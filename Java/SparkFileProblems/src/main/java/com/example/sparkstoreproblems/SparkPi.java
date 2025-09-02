// 4,11,14,20

package com.example.sparkstoreproblems;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.*;

public class SparkPi {
    public static void main(String[] args){
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Spark Store Problems Xls")
                .getOrCreate();

        String filePath = "./src/main/resources/store.csv";

        StructType schema = new StructType(
                new StructField[]{
                        DataTypes.createStructField("Id", DataTypes.IntegerType, false),
                        DataTypes.createStructField("OrderID", DataTypes.StringType, false),
                        DataTypes.createStructField("OrderDate", DataTypes.DateType, true),
                        DataTypes.createStructField("ShipDate", DataTypes.DateType, true),
                        DataTypes.createStructField("ShipMode", DataTypes.StringType, false),
                        DataTypes.createStructField("CustomerID", DataTypes.StringType, false),
                        DataTypes.createStructField("CustomerName", DataTypes.StringType, false),
                        DataTypes.createStructField("Segment", DataTypes.StringType, false),
                        DataTypes.createStructField("State", DataTypes.StringType, false),
                        DataTypes.createStructField("PostalCode", DataTypes.StringType, false),
                        DataTypes.createStructField("Region", DataTypes.StringType, false),
                        DataTypes.createStructField("ProductID", DataTypes.StringType, false),
                        DataTypes.createStructField("Category", DataTypes.StringType, false),
                        DataTypes.createStructField("ProductName", DataTypes.StringType, false),
                        DataTypes.createStructField("Sales", DataTypes.DoubleType, true),
                        DataTypes.createStructField("Quantity", DataTypes.IntegerType, false),
                }
        );
        Dataset<Row> df = spark.read().option("header", "true").schema(schema).csv(filePath);

        System.out.println("Total Rows in File : " + df.count());

        printSchema(df);
        totalSalesPerProduct(df, "ProductName", "Sales");
        runningSumOverTime(df, "OrderDate", "Sales");
        movingAverage(df, "OrderDate", "Sales", 3);

        spark.stop();
    }

    // 4. Load a CSV file using Java Spark and print the schema.
    private static void printSchema(Dataset<Row> df){
        System.out.println("Schema:");
        df.printSchema();
    }

    // 11. Implement a Java Spark job to calculate total sales per product from a sales dataset.
    private static void totalSalesPerProduct(Dataset<Row> df, String productCol, String salesCol) {
        System.out.println("Total Sales Per Product:");
        df.groupBy(col(productCol))
                .agg(round(sum(col(salesCol)), 2).alias("Total Sales"),
                        sum(col("Quantity")).alias("Total Quantity"))
                .orderBy(col("Total Sales").desc())
                .show();
    }

    // 14. Write a Java Spark application to compute running total of sales over time.
    private static void runningSumOverTime(Dataset<Row> df, String dateCol, String salesCol) {
        System.out.println("Running Total of Sales Over Time:");
        WindowSpec w = Window.orderBy(col(dateCol))
                .rowsBetween(Window.unboundedPreceding(), Window.currentRow());
        df.withColumn("Running Total Sales", round(sum(col(salesCol)).over(w),2))
                .withColumn("Running Total Quantity", round(sum(col("Quantity")).over(w), 2))
                .select(col(dateCol), col(salesCol), col("Running Total Sales"), col("Running Total Quantity"))
                .orderBy(col(dateCol))
                .show();
    }

    // 20. Create a Java Spark job to calculate moving average of a numeric column.
    private static void movingAverage(Dataset<Row> df, String dateCol, String salesCol, int windowSize) {
        System.out.println("Moving Average of Sales:");
        WindowSpec w = Window.orderBy(col(dateCol))
                .rowsBetween(-windowSize + 1, 0);
        df.withColumn("Moving Average Sales", round(avg(col(salesCol)).over(w),2))
                .select(col(dateCol), col(salesCol), col("Moving Average Sales"))
                .orderBy(col(dateCol))
                .show();
    }
}
