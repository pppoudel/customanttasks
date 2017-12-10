/* Copyright 2017 ppoudel@sysgenius.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/

package com.sysgenius.tools.ant.customtask;

/**
 * Custom Ant Task: IsInList
 * Returns true if given string is in the list (delimited) of strings. 
 * Comparison can be regular or Java RegEx based.
 * @author Purna Poudel
 * Date: 2011
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.taskdefs.condition.Condition;

public class IsInList extends ProjectComponent implements Condition {
	  private String value;
	  private String valueList;
	  private String delimiter;
	  private boolean casesensitive = false;
	  private boolean isRegEx=false;
	  
	  /**
	   * Sets the value/item.
	   * @param value value/item to be looked in the given list.
	   */
	  public void setValue(String value)
	  {
		  	this.value = value.trim();
	  }
	  
	  /**
	   * Sets the value/item list.
	   * @param valueList value/item list.
	   */
	  public void setValueList(String valueList)
	  {
		  	this.valueList = valueList.trim();
	  }
	  
	  /**
	   * Sets the delimiter that separates the value/items in the list.
	   * @param delimiter delimiter that separates the value/items in the list.
	   */
	  
	  public void setDelimiter(String delimiter)
	  {
		  	this.delimiter = delimiter.trim();
	  }
	  
	  /**
	   * Sets the case sensitivity that is used to search the item/value in the list.
	   * @param casesensitive true or false
	   */
	  public void setCasesensitive(boolean casesensitive)
	  {
		  	this.casesensitive = casesensitive;
	  }
	  
	  /**
	   * Sets whether or not the value/item search in the list is based on regular expression
	   * @param isRegEx true or false
	   */
	  public void setIsRegEx(boolean isRegEx){
		  this.isRegEx = isRegEx;
	  }
	  
	  /**
	   * Ant eval method.
	   */
	  public boolean eval() throws BuildException
	  {
		  boolean _isInList=false;  
		  if (this.valueList == null) {
		      throw new BuildException("No list of values specified for IsInList condition");
		    }
		    if (this.value == null) {
		      throw new BuildException("No value specified to test IsInList condition");
		    }
		    if (!this.casesensitive)
		    {
		      this.value = this.value.toLowerCase();
		      this.valueList = this.valueList.toLowerCase();
		    }
		    String[] sArray = this.valueList.split(this.delimiter);
		    ArrayList<String> _list = new ArrayList<String>(Arrays.asList(sArray));
		    if(!this.isRegEx){
		    	_isInList = _list.contains(this.value);
		    } else {
		    	for(String s:_list) {
		    		Pattern pt = Pattern.compile(s);
		    		//System.out.println("Performing RegEx Matching using pattern: "+s+ " with value from list: "+this.value);
		    		_isInList = pt.matcher(this.value).matches();
		    		if(_isInList){
		    			//System.out.println("Matched. Skipping the rest.");
		    			break;		    			
		    		} 
		    	}
		    }
		    return _isInList;
	  }
	  
	  // The following main method is just for testing purpose. It is not required in order to run as Ant task.
	  /*
	  public static void main(String[] args)
	  {
		    IsInList myList = new IsInList();
		    myList.setValueList("ci;Inting.*;release;SystemOut_16.01.23.log;native_stdout.*;native_stderr.*");
		    myList.setValue("native_stdout.log");
		    myList.setDelimiter(";");
		    myList.setCasesensitive(false);
		    myList.setIsRegEx(true);
		    System.out.println("Resullt is " + myList.eval());
	  }*/
}

