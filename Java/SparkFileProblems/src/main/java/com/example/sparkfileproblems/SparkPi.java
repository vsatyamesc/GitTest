// Covers 1,2,3,12,22

package com.example.sparkfileproblems;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.*;

import static org.apache.spark.sql.functions.col;


public final class SparkPi {
    public static void main(String[] args) {

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Spark Problems with File")
                .getOrCreate();
        String filePath = "./src/main/resources/sample.txt";
        Dataset<Row> txtFile = spark.read().text(filePath);

        prinWordCount(txtFile);
        txtFile.show();
        FilterContaining(txtFile, "the");
        printCountOfEachCharacter(txtFile);

        spark.stop();
    }

    // 1. Create a Spark application in Java to count words in a text file.
    // 12. Create a Java Spark program to find the top 5 most frequent words in a text file.
    // 22. Implement a Java Spark job to tokenize sentences and count unique words.
    private static void prinWordCount(Dataset<Row> txtFile) {

        Dataset<String> words = txtFile.flatMap((FlatMapFunction<Row, String>) row -> {
            String line = row.getString(0).toLowerCase();
            return Arrays.asList(line.split(" ")).iterator();
        }, Encoders.STRING());

        long wordCount = words.count();

        words.show();
        System.out.println("Total words: " + wordCount);

        // Top 5 Frequent words, this includes SPACE so offset 1
        Dataset<Row> wordCounts = words
                .withColumnRenamed("value", "word")
                .groupBy(col("word"))
                .count()
                .orderBy(col("count").desc())
                .offset(1)
                .limit(5);
        Dataset<Row> occuredOnlyOnce = words
                .withColumnRenamed("value", "occuredOnlyOnce")
                .groupBy(col("occuredOnlyOnce"))
                .count()
                .where(col("count").equalTo(1));
        System.out.println("Top 5 most frequent words:");
        wordCounts.show();

        System.out.println("Only Occured Once/Unique Words:");
        occuredOnlyOnce.show();

        System.out.println("Tokenize Words:"); // One-Hot Encoding

    }

    // 2. Implement a Java Spark program to filter lines containing a specific keyword.
    private static void FilterContaining(Dataset<Row> txtFile, String keyword) {
        Dataset<Row> filtered = txtFile.filter((FilterFunction<Row>) row -> row.getString(0).contains(keyword));
        System.out.println("Lines containing '" + keyword + "':");
        filtered.show();
    }

    // 3. Write a Java Spark job to count occurrences of each character in a text file.
    private static void printCountOfEachCharacter(Dataset<Row> txtFile){
        Map<Character, Integer> CharCount= new HashMap<>();
        txtFile.collectAsList().forEach(row -> {
            String line = row.getString(0);
            for (char c : line.toCharArray()) {
                CharCount.put(c, CharCount.getOrDefault(c, 0) + 1);
            }
        });
        System.out.println("Character counts: " + CharCount);
    }
}