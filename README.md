# hadoop-start
Hadoop start is a Mavenized Hadoop project which some people should find useful when starting out with Hadoop.

## Setting up Hadoop
Download [Apache Hadoop](http://hadoop.apache.org/releases.html#18+November%2C+2014%3A+Release+2.6.0+available) (I'm currently using v2.6.0) and unzip to your desired location.
Mac OS X instructions:

+ Find your Java installation:

````
/usr/libexec/java_home 1.8
````

+ Set JAVA_HOME environmental variable in your ~/bash_profile

````
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home
````

+ Enable SSH from System Preference -> Sharing -> Remote Login. Check it works:

````
$ ssh localhost
If you cannot ssh to localhost without a passphrase, execute the following commands:

$ ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
$ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
````

+ Unzip hadoop. Duh.

+ Update you .bash_profile to add Hadoop to $PATH (on Windows add it as an environment variable), this way you can call the hadoop commands from anywhere. e.g.

````
export HADOOP_INSTALL=/Users/......../hadoop/dist/hadoop-2.6.0
export PATH=$HADOOP_INSTALL/bin:$HADOOP_INSTALL/sbin:$PATH
````

+ Update etc/core-site.xml 

````
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/Users/ichishty/cloud/development/servers/hadoop/data</value>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost</value> <!-- Add port 8020 for spring xd -->
    </property>
</configuration>
````

+ Update etc/hdfs-site.xml

````
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.support.append</name>
        <value>true</value>
    </property>
    <property>
        <name>dfs.webhdfs.enabled</name>
        <value>true</value>
    </property>
</configuration>
````

+ Update etc/mapred-site.xml

````
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
````

+ Update etc/yarn-site.xml

````
<configuration>
    <!-- Site specific YARN configuration properties -->
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>localhost</value>
    </property>
</configuration>
````

+ Format HDFS

If you reformat then you might a namenode and datanode cluster ID mismatch. If this happens then update the Cluster ID. 
[Refer to this for help] (http://stackoverflow.com/questions/30521474/hadoop-hdfs-formatting-gets-error-failed-for-block-pool) 

````    
hdfs namenode fs
````

+ Starting DFS (once started UI: http://localhost:50070/)

````
$ sbin/start-dfs.sh
````

+ Starting YARN (once started UI: http://localhost:8088/)

````
$ sbin/start-yarn.sh
````

+ Starting job history (http://localhost:19888/jobhistory)

````
/mr-jobhistory-daemon.sh start historyserver
````

+ Use 'jps' to check everything is running

````
Imams-MacBook-Pro:bin ichishty$ jps
15456 JobHistoryServer
18161 NameNode
22341 RemoteMavenServer
18234 DataNode
5451 
17212 NodeManager
17133 ResourceManager
26639 Jps
Imams-MacBook-Pro:bin ichishty$ 
````

+ To stop 

````
stop-dfs.sh
stop-yarm.sh
mr-jobhistory-daemon.sh stop historyserver
````

+ Create a user directory

````
hadoop fs -mkdir -p /Users/XXXX
````

#### Some basic concepts

I'm not going into much detail, but rather provide some basic notes (work in progress).

Big Data Vs:

````
1. Volume
2. Velocity
3. Variety
4. Veracity (is the data clean? transformations required?)
````

+ Two main components in Hadoop:

````
HDFS - distributed file system manages data across the cluster and makes it available for processing

YARN (yet another resource negotiator)- resource manager that manages and schedules computational assets and an application deployment framework for running processing jobs.
````

+ HDFS & YARN are implemented by several independent daemon processes:

````
1. NameNode (Master) - contains metadata for HDFS ops
2. SecondaryNameNode (master) - performs housekeeping tasks for hdfs
3. DataNode (worker) - stores and manages hdfs blocks on a node. reports teh health and status for an individual data store.
4. Resource Manager (master) - allocates and monitors processing jobs for MapReduce and other apps.
5. Node Manager (worker) - runs and manages processing tasks on an individual node.
6. Application Master (master) - every app is assigned an app manager that coordinates a particular app as sched by the resource manager
````

+ Types of nodes

````
1. Masters - these nodes run the global management process
2. Worker nodes - run local data and application processes
````

+ Web UI ports

````
NameNode - 50070
Resource Manager 8088
MapReduce Job History 19888
````

+ DFS

````
- is a WORM storage (Write Once Read Many)
- files are split into block, usually 64MB or 128MB
- blocks allows big files to be split and  distributed to many machines
- block replication defaults to about 3, but in our case we have set it to 1 - see dfs.replication in hdfs-site.xml.
- master NameNode keeps track of what blocks make up files and their locations. Communicates with DataNode to manage local stores of the blocks.
- So when client app wants to read file it requests metadata from NameNode to locate blocks then communicates with the DataNodes to read the data.
IF NameNode fails then cluster is down!!
````

+ MapReduce

````
A job is a full program, the complete execution of Map and Reduce functions across all input data.

A job is composed of many tasks (execution of a single attempt at Map or Reduce)

A task attempt is a particular instance of a task.

````

+ High level view

````
1. Local data is picked up from HDFS and processed as key-value pairs.
2. Mapper is selected and called with key-value pair.
3. Mapper does some logic and spits out key-value pair.
4. Shuffle occurs using the key from the mapper output.
5. Reducer called and then creates the final key-value pairs to the output path.
````

### Starting the applications

Once you've started Hadoop you can run any of the application by running the 'Driver' classes. You must provide two arguments:
+ input path - file/directory/file patterns.
+ output path - a new location where the output will be written to.