package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		Set<String> idSet = new HashSet<>();
        List<Member> nonDuplicateMembers = new ArrayList<>();
        for (Member member : membersFromFile) {
            if (!idSet.contains(member.getId())) {
                idSet.add(member.getId());
                nonDuplicateMembers.add(member);
            }
        }
        return nonDuplicateMembers;
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		Map<String, List<Member>> membersByState = new HashMap<>();
        for (Member member : validMembers) {
            String state = member.getState();
            if (!membersByState.containsKey(state)) {
                membersByState.put(state, new ArrayList<>());
            }
            membersByState.get(state).add(member);
        }
        return membersByState;
	}

	public static void main( String[] args ) {
		
		Main converter = new Main();
        File inputFile = new File("Members.txt");
        String outputFilePath = "output";
        String outputFileName = "outputFile.csv";
        try {
        	File outputDir = new File(outputFilePath);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            converter.convert(inputFile, outputFilePath, outputFileName);
            System.out.println("Conversion completed successfully.");
        } catch (Exception e) {
            System.err.println("Error during conversion: " + e.getMessage());
            e.printStackTrace();
        }
	}

}
