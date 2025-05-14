package javaStreams;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaStreams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> intList = new ArrayList<Integer>();
		intList.addAll(Arrays.asList(3,2,2,7,5,1,9,7));
		
		Long countList = Arrays.asList("Aman","Himanshu","Ankit","Tanisha","Fatehjot","Simone","Ankita","Chesta").stream()
		.filter(s->s.startsWith("A")).count();
		
		Arrays.asList("Aman","Himanshu","Ankit","Tanisha","Fatehjot","Simone","Ankita","Chesta").stream()
		.filter(s->s.endsWith("a")).sorted().forEach(s->System.out.println(s));
		
		System.out.println(countList);
		
		intList.stream().distinct().sorted().forEach(s->System.out.println(s));

	}

}
