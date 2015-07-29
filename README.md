# hadoop-start
Hadoop start is a Mavenized Hadoop project which some people should find useful when starting out with Hadoop.

## Setting up Hadoop
Download [Apache Hadoop](http://hadoop.apache.org/releases.html#18+November%2C+2014%3A+Release+2.6.0+available) (I'm currently using v2.6.0) and unzip to your desired location.
Mac OS X instructions:

+ Find your Java installation:

> /usr/libexec/java_home 1.8

+ Set JAVA_HOME environmental variable in your ~/bash_profile

> export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0.jdk/Contents/Home

+ Enable SSH from System Preference -> Sharing -> Remote Login. Check it works:

> $ ssh localhost
> If you cannot ssh to localhost without a passphrase, execute the following commands:
>
> $ ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
> $ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys

+ Unzip hadoop. Duh.

+ Update you .bash_profile to add Hadoop to $PATH (on Windows add it as an environment variable), this way you can call the hadoop commands from anywhere. e.g.

> export HADOOP_INSTALL=/Users/......../hadoop/dist/hadoop-2.6.0

> export PATH=$HADOOP_INSTALL/bin:$HADOOP_INSTALL/sbin:$PATH

+ Update etc/core-site.xml 

>&lt;configuration&gt;<br/>
>&nbsp;   &lt;property&gt;<br/>
>&nbsp; &nbsp;      &lt;name&gt;hadoop.tmp.dir&lt;/name&gt;<br/>
>&nbsp; &nbsp;      &lt;value&gt;/Users/ichishty/cloud/development/servers/hadoop/data&lt;/value&gt;<br/>
>&nbsp;    &lt;/property&gt;<br/>
> <br/>
>&nbsp;    &lt;property&gt;<br/>
>&nbsp; &nbsp;        &lt;name&gt;fs.defaultFS&lt;/name&gt;<br/>
>&nbsp; &nbsp;        &lt;value&gt;hdfs://localhost&lt;/value&gt; &lt;!-- Add port 8020 for spring xd --&gt;<br/>
>&nbsp;    &lt;/property&gt;<br/>
> <br/>
> &lt;/configuration&gt;<br/>

+ Update etc/hdfs-site.xml

> &lt;configuration&gt;<br/>
> &nbsp;  &lt;property&gt;<br/>
> &nbsp; &nbsp;       &lt;name&gt;dfs.replication&lt;/name&gt;<br/>
> &nbsp; &nbsp;       &lt;value&gt;1&lt;/value&gt;<br/>
> &nbsp;   &lt;/property&gt;<br/>
> &nbsp;   &lt;property&gt;<br/>
> &nbsp; &nbsp;       &lt;name&gt;dfs.support.append&lt;/name&gt;<br/>
> &nbsp; &nbsp;       &lt;value&gt;true&lt;/value&gt;<br/>
> &nbsp;   &lt;/property&gt;<br/>
> &nbsp;   &lt;property&gt;<br/>
> &nbsp; &nbsp;       &lt;name&gt;dfs.webhdfs.enabled&lt;/name&gt;<br/>
> &nbsp; &nbsp;       &lt;value&gt;true&lt;/value&gt;<br/>
> &nbsp;   &lt;/property&gt;<br/>
> &lt;/configuration&gt;<br/>

+ Update etc/mapred-site.xml

> &lt;configuration&gt;<br/>
> &nbsp;     &lt;property&gt;<br/>
> &nbsp; &nbsp;         &lt;name&gt;mapreduce.framework.name&lt;/name&gt;<br/>
>  &nbsp; &nbsp;        &lt;value&gt;yarn&lt;/value&gt;<br/>
>  &nbsp;    &lt;/property&gt;<br/>
> &lt;/configuration&gt;<br/>

+ Update etc/yarn-site.xml

> &lt;configuration&gt;<br/>
> &nbsp; &lt;!-- Site specific YARN configuration properties --&gt;<br/>
> &nbsp; &lt;property&gt;<br/>
>  &nbsp; &nbsp;    &lt;name&gt;yarn.nodemanager.aux-services&lt;/name&gt;<br/>
> &nbsp; &nbsp;     &lt;value&gt;mapreduce_shuffle&lt;/value&gt;<br/>
> &nbsp; &lt;/property&gt;<br/>
> &nbsp; &lt;property&gt;<br/>
> &nbsp; &nbsp;     &lt;name&gt;yarn.resourcemanager.hostname&lt;/name&gt;<br/>
> &nbsp; &nbsp;     &lt;value&gt;localhost&lt;/value&gt;<br/>
> &nbsp; &lt;/property&gt;<br/>
> &lt;/configuration&gt;<br/>

+ Format HDFS

If you reformat then you might a namenode and datanode cluster ID mismatch. If this happens then update the Cluster ID. 
[Refer to this for help] (http://stackoverflow.com/questions/30521474/hadoop-hdfs-formatting-gets-error-failed-for-block-pool) 

> hdfs namenode fs

+ Starting DFS (once started UI: http://localhost:50070/)

> $ sbin/start-dfs.sh

+ Starting YARN (once started UI: http://localhost:8088/)

> $ sbin/start-yarn.sh

+ Starting job history (http://localhost:19888/jobhistory)

> /mr-jobhistory-daemon.sh start historyserver

+ To stop 

> stop-dfs.sh<br/>
> stop-yarm.sh<br/>
> mr-jobhistory-daemon.sh stop historyserver

+ Create a user directory

> hadoop fs -mkdir -p /Users/XXXX

## Starting the applications

Once you've started Hadoop you can run any of the application by running the 'Driver' classes. You must provide two arguments:
+ input path - file/directory/file patterns.
+ output path - a new location where the output will be written to.