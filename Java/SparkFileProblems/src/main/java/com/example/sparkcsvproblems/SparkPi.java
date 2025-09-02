// 4,5,6,7,8,9,15,16,19,21
package com.example.sparkcsvproblems;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import javax.xml.crypto.Data;

import static org.apache.spark.sql.functions.*;

public class SparkPi {
    public static void main(String []args){

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Spark Problems with CSV")
                .getOrCreate();

        StructType schema1 = new StructType(
                new StructField[]{
                        DataTypes.createStructField("Index", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Name", DataTypes.StringType, false),
                        DataTypes.createStructField("Description", DataTypes.StringType, false),
                        DataTypes.createStructField("Category", DataTypes.StringType, false),
                        DataTypes.createStructField("Price", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Stock", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Color", DataTypes.StringType, false),
                        DataTypes.createStructField("Size", DataTypes.StringType, false)

                }
        );

        StructType schema2 = new StructType(
                new StructField[]{
                        DataTypes.createStructField("Index", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Name", DataTypes.StringType, false),
                        DataTypes.createStructField("Description", DataTypes.StringType, false),
                        DataTypes.createStructField("Category", DataTypes.StringType, false),
                        DataTypes.createStructField("Price", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Stock", DataTypes.IntegerType, false),
                        DataTypes.createStructField("Color", DataTypes.StringType, false),
                        DataTypes.createStructField("Size", DataTypes.StringType, false),
                        DataTypes.createStructField("Availability", DataTypes.StringType, false),
                        DataTypes.createStructField("InternalID", DataTypes.IntegerType, false)

                }
        );
        Dataset<Row> csvFile1 = spark.read().option("header", "true").schema(schema1).csv("./src/main/resources/sample.csv");
        Dataset<Row> csvFile2 = spark.read().option("header", "true").schema(schema2).csv("./src/main/resources/sample_og.csv");

        System.out.println("The Number of Rows in the CSV File is: " + csvFile1.count());
        printSchema(csvFile1);
        Dataset<Row> new_joined = joinOnKey(csvFile1, csvFile2);
        groupByAndCountBy(csvFile1, "Category");
        removeDuplicates(csvFile1);
        sortByColumnDesc(csvFile1, "Price");
        convertCsvToParquet(csvFile1, "./src/main/resources/output_parquet");
        filterNullOrEmpty(csvFile1);
        performJoins(csvFile1, csvFile2, "Index");
        simpleETLPipeline(new_joined, "./src/main/resources/problem21_parquet");

        spark.stop();
    }

    // 4. Load a CSV file using Java Spark and print the schema.
    private static void printSchema(Dataset<Row> df){
        System.out.println("Schema:");
        df.printSchema();
    }
    // 5. Implement a Java Spark application to compute average, min, max of numeric column in CSV.
    private static void computeStats(Dataset<Row> df){
        System.out.println("Compute Statistics - Average, Min, Max:");
        Dataset<Row> stats = df.agg(
                avg(col("Price")).alias("Average Price"),
                min(col("Price")).alias("Min Price"),
                max(col("Price")).alias("Max Price")
        );
        stats.show();
    }
    // 6. Write a Java Spark program to join two datasets based on a common key.
    private static Dataset<Row> joinOnKey(Dataset<Row> df1, Dataset<Row> df2){
        System.out.println("Join on Key: Index");
        Dataset<Row> df2Extra = df2.select(col("Index").alias("Id"), col("Availability"), col("InternalID"));
        Dataset<Row> joined = df1.join(df2Extra, df1.col("Index").equalTo(df2Extra.col("Id")), "outer");
        joined.show();
        joined.drop("Id");
        return joined;
    }

    // 7. Implement a Java Spark job to group data by a column and count rows per group.
    private static void groupByAndCountBy(Dataset<Row> df, String column){
        System.out.println("Grouping by Column: " + column);
        Dataset<Row> grouped = df.groupBy(col(column).alias("Grouped By")).agg(count("*").alias("Item Count"));
        grouped.show();
    }

    // 8. Create a Java Spark program to remove duplicates from a dataset.
    private static void removeDuplicates(Dataset<Row> df){
        System.out.println("Removing Duplicates in Dataset: " + df);
        Dataset<Row> distinct = df.dropDuplicates("Index");
        distinct.show();
    }
    // 9. Write a Java Spark application to sort data by a numeric column in descending order.
    private static void sortByColumnDesc(Dataset<Row> df, String column){
        System.out.println("Sorting by Column: " + column);
        Dataset<Row> sorted = df.orderBy(col(column).desc());
        sorted.show();
    }

    // 15. Use Java Spark to convert a CSV file to Parquet format.
    private static void convertCsvToParquet(Dataset<Row> df, String outputPath){
        System.out.println("Converting CSV to Parquet at: " + outputPath);
        df.write().mode("overwrite").parquet(outputPath);
    }

    // 16. Implement a Java Spark program to filter out null or empty values from a dataset.
    private static void filterNullOrEmpty(Dataset<Row> df){
        System.out.println("Filtering Null or Empty values in Dataframe : " + df);
        Dataset<Row> filtered = df.na().drop();
        // Dataset<Row> filtered = df.na().drop("any", new String[]{"Index", "Name"});
        filtered.show();
    }

    // 19. Write a Java Spark program to perform inner, left, and right joins on datasets.
    private static void performJoins(Dataset<Row> df1, Dataset<Row> df2, String key) {
        System.out.println("Performing Inner Join on Key: " + key);
        Dataset<Row> innerJoin = df1.join(df2, df1.col(key).equalTo(df2.col(key)), "inner");
        innerJoin.show();

        System.out.println("Performing Left Join on Key: " + key);
        Dataset<Row> leftJoin = df1.join(df2, df1.col(key).equalTo(df2.col(key)), "left");
        leftJoin.show();

        System.out.println("Performing Right Join on Key: " + key);
        Dataset<Row> rightJoin = df1.join(df2, df1.col(key).equalTo(df2.col(key)), "right");
        rightJoin.show();
    }

    // 21. Use Java Spark to perform a simple ETL pipeline: read CSV, transform, and write to Parquet.
    private static void simpleETLPipeline(Dataset<Row> df1, String outputPath){
        System.out.println("Simple ETL Pipeline, Join Based on Key and Save in Parquet Format at: " + outputPath);
        convertCsvToParquet(df1, outputPath);
    }
}
