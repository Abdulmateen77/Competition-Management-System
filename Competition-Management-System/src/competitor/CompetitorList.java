package competitor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Arrays;

//Competitor list class detailss
public class CompetitorList {
    private static List<AMScompetitor> competitorList;

    public CompetitorList() {
        this.competitorList = new ArrayList<>();
    }


    public static void addCompetitor(AMScompetitor competitor) {
        competitorList.add(competitor);
    }


    public List<AMScompetitor> getCompetitors() {
        return List.of(competitorList.toArray(new AMScompetitor[0]));
    }


     //Function to read competitor details file
    public static CompetitorList readCompetitorDetails(String fileName){
        CompetitorList competitorList = new CompetitorList();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = fileReader.readLine()) != null){
                AMScompetitor competitor = parseDetailsofLine(line);
                if(competitor != null){
                    addCompetitor(competitor);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return competitorList;
    }

    //Saves Competitor score to a file
    public void saveCompetitorScoresToFile(String filePath, AMScompetitor competitorToSave) {
        try {
            //Creates a new list with updated scores
            List<AMScompetitor> updatedCompetitors = competitorList.stream()
                    .map(competitor -> {
                        if (competitor.getCompetitorNumber() == competitorToSave.getCompetitorNumber()) {

                            return new AMScompetitor(
                                    competitor.getCompetitorNumber(),
                                    competitor.getName(),
                                    competitor.getAge(),
                                    competitor.getGender(),
                                    competitor.getCountry(),
                                    competitorToSave.getScores() // Updates only scores
                            );
                        }
                        return competitor;
                    })
                    .collect(Collectors.toList());

            //Writes the entire list back to the file
            try (FileWriter writer = new FileWriter(filePath)) {
                for (AMScompetitor competitor : updatedCompetitors) {
                    writer.append(String.valueOf(competitor.getCompetitorNumber())).append(",");
                    writer.append(competitor.getName()).append(",");
                    writer.append(String.valueOf(competitor.getAge())).append(",");
                    writer.append(competitor.getGender()).append(",");
                    writer.append(competitor.getCountry()).append(",");
                    writer.append(String.valueOf(competitor.getScores()[0])).append(",");
                    writer.append(String.valueOf(competitor.getScores()[1])).append(",");
                    writer.append(String.valueOf(competitor.getScores()[2])).append(",");
                    writer.append(String.valueOf(competitor.getScores()[3])).append(",");
                    writer.append("\n");
                }

                System.out.println("Competitor scores successfully updated and saved to file: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error saving competitor scores to file: " + filePath);
            e.printStackTrace();
        }
    }


    private static AMScompetitor parseDetailsofLine(String line) throws ParseException {
        String[] parts = line.split(",");
        int competitorNumber = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        String gender = parts[3].trim();
        String country = parts[4].trim();

        //Dynamically read scores based on the length
        int[] scores = new int[parts.length - 5];
        for (int i = 5; i < parts.length; i++) {
            scores[i - 5] = Integer.parseInt(parts[i].trim());
        }

        return new AMScompetitor(competitorNumber, name, age, gender, country, scores);
    }


    public void writeCompetitorDetails(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (AMScompetitor competitor : competitorList) {
                String line = String.format("%d,%s,%d,%s,%s,%s,%s",
                        competitor.getCompetitorNumber(), competitor.getName(), competitor.getAge(),
                        competitor.getGender(), competitor.getCountry(), AMScompetitor.getEmail(),
                        AMScompetitor.getCategory());
                writer.write(line + System.lineSeparator());
            }
        }
    }
    public void displayShortDetails(int competitorNumber) {
        System.out.println("Short Details for Competitor " + competitorNumber + ":");
        for (AMScompetitor competitor : competitorList) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                System.out.println(competitor.getShortDetails());
            }
        }
        System.out.println("Competitor not found.");
    }


    public void generateFinalReport(String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Table of competitors with full details
            writer.write("Competitors Table:\n");
            for (AMScompetitor competitor : competitorList) {
                if (competitor != null) {
                    writer.write(competitor.getfullDetails() + "\n");
                }
            }

            //Details of the competitor with the highest overall score
            AMScompetitor highestScorer = getHighestScorer();
            writer.write("\nDetails of the Highest Scorer:\n");
            if (highestScorer != null) {
                writer.write(highestScorer.getfullDetails() + "\n");
            }
            //Other summary statistics
            writer.write("\nSummary Statistics:\n");
            writer.write("Average Overall Score: " + calculateAverageOverallScore() + "\n");
            writer.write("Total Scores: " + calculateTotalScores() + "\n");
            writer.write("Max Score: " + findMaxScore() + "\n");
            writer.write("Min Score: " + findMinScore() + "\n");

            //Frequency report
            writer.write("\nFrequency Report:\n");
            Map<Integer, Long> scoreFrequency = calculateScoreFrequency();
            scoreFrequency.forEach((score, count) -> {
                try {
                    writer.write("Score " + score + ": " + count + " times\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public double calculateAverageOverallScore() {
        return competitorList.stream()
                .mapToDouble(AMScompetitor::getOverallScore)
                .average()
                .orElse(0.0);
    }

    public int calculateTotalScores() {
        return competitorList.stream()
                .mapToInt(competitor -> Arrays.stream(competitor.getScoreArray()).sum())
                .sum();
    }

    public int findMaxScore() {
        return competitorList.stream()
                .flatMapToInt(competitor -> Arrays.stream(competitor.getScoreArray()))
                .max()
                .orElse(0);
    }

    public int findMinScore() {
        return competitorList.stream()
                .flatMapToInt(competitor -> Arrays.stream(competitor.getScoreArray()))
                .min()
                .orElse(0);
    }

    private Map<Integer, Long> calculateScoreFrequency() {
        return competitorList.stream()
                .flatMapToInt(competitor -> Arrays.stream(competitor.getScoreArray()))
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private AMScompetitor getHighestScorer() {
        return competitorList.stream()
                .max(Comparator.comparingDouble(AMScompetitor::getOverallScore))
                .orElse(null);
    }


}
