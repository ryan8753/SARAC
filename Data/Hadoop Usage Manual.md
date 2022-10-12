# Hadoop Usage Manual

# Environment Setting

## Java Dependency

```
dependencies {
    implementation 'org.apache.hadoop:hadoop-common:3.3.2'
		implementation 'org.apache.hadoop:hadoop-mapreduce-client-core:3.3.2'
}

```

# Getting Data for Using Hadoop

## Queries

- book 테이블의 description 데이터 중, 유효한 데이터만 가져오는 query

```sql
SELECT description
FROM sarac.book
where description != ""
&& description not like "[해외주문원서]%"
&& description !="#NAME?"
&& description not like "%.0"
&& description not like "POD로 제작되는%"
```

### 파일

- csv는 인코딩 이슈가 있으므로, txt로 export 필요

# Use Hadoop Cluster

## Access To Cluster

- {provided_user_name}@cluster.ssafy.io

![Untitled](Hadoop%20Usage%20Manual/Untitled.png)

- Load EC2 access key(needed to convert pem to ppk)

![Untitled](Hadoop%20Usage%20Manual/Untitled%201.png)

## Shell Script

```bash
# Please upload JAR file which contains MapReduce algorithm
# In this script, data.jar is JAR file,
# desc.txt is input file to run MapReduce.

# create home directory in HDFS
hdfs dfs -mkdir -p .

# prevent other users' access (Optional)
hdfs dfs -chmod 0700 .

# upload file to get result from Hadoop MapReduce
hdfs dfs -put desc.txt

# remove previous results
hdfs dfs -rm -r output

# run your MapReduce Algorithm
hadoop jar data.jar com.sarac.data.Wordcount desc.txt output

# copy current results from HDFS to linux directory
hdfs dfs -copyToLocal output/* /home/j7a505/hadoop
```
