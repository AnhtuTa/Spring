package beans;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class JavaCollection2 {
	List<String> cityList;
	Set<String> citySet;
	Map<String, String> cityMap;
	Properties cityProp;
	
	public List<String> getCityList() {
		System.out.println("This is getCityList(), cityList = " + cityList);
		return cityList;
	}
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	public Set<String> getCitySet() {
		System.out.println("This is getCitySet(), citySet = " + citySet);
		return citySet;
	}
	public void setCitySet(Set<String> citySet) {
		this.citySet = citySet;
	}
	public Map<String, String> getCityMap() {
		System.out.println("This is getCityMap(), cityMap = " + cityMap);
		return cityMap;
	}
	public void setCityMap(Map<String, String> cityMap) {
		this.cityMap = cityMap;
	}
	public Properties getCityProp() {
		System.out.println("This is getCityProp(), cityProp = " + cityProp);
		return cityProp;
	}
	public void setCityProp(Properties cityProp) {
		this.cityProp = cityProp;
	}
	
}
