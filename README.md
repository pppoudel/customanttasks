# Custom Ant Task IsInList  

## What is this?  
This is a Custom Ant Task that makes it easy to find an item/value in the list of items/values. See the examples below:  
See the details in my blog [Custom Ant Task IsInList] (https://purnapoudel.blogspot.com/2017/03/custom-ant-task-isinlist.html)


### How to use it?  
Follow the steps below:  


1. Make sure isinlist-\<version\>.jar file is in your classpath. You can do it  
   either by adding it into your $ANT_HOME/lib directory or   
	 by defining a custom library path like below and making a reference to it.
```   
<path id="ant.opt.lib.path">  
	<fileset dir="${basedir}/lib">  
		<include name="isinlist-1.0.0.0.jar"/>  
	</fileset>  
</path>  
```  
2. Next, define the task, below is one of the ways:  
   ```<typedef classname="com.sysgenius.tools.ant.customtask.IsInList" name="isinlist" classpathref="ant.opt.lib.path"/>```  

3. Use it within your build file as follows:  
  #### Example 1:  
	You have a list of items like "ci;Inting.*;release;SystemOut_16.01.23.log;native_stdout.*;native_stderr.*" separated by ";".
	Here you need to find out whether or not any item starting with "SystemOut" exists. In this case you can do lookup using regular expression.  
	In your build file, you'll need to have:  
	```
		<property name="item.list" value="ci;Inting.*;release;SystemOut_16.01.23.log;native_stdout.*;native_stderr.*"/>    
		<property name="regex.item.name" value="native_stdout.log"/>  
    <isinlist casesensitive="false" delimiter=";" value="${regex.item.name}" valueList="${item.list}" isRegEx="true"/>  
	```  
  #### Example 2:  
  You have a list of items like "ci;Inting.*;release;SystemOut_16.01.23.log;native_stdout.*;native_stderr.*" separated by ";".   
	Here you need to find out whether an item called "release" exists.   
	In this case you can use regular lookup. 
	
	```   
		<property name="item.list" value="ci;Inting.*;release;SystemOut_16.01.23.log;native_stdout.*;native_stderr.*"/>  
		<property name="regular.item.name" value="release"/>  
		<isinlist casesensitive="false" delimiter=";" value="${regular.item.name}" valueList="${item.list}" isRegEx="false"/>
  ```
  
See the sample-usage.xml file for detail usage.  
