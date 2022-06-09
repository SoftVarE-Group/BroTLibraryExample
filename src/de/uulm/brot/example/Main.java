package de.uulm.brot.example;

import java.util.List;
import java.util.stream.Stream;

import tubs.cs.satencodings.generation.AtMostSATEncoding;
import tubs.cs.satencodings.generation.AtMostSATEncoding.EncodingOptions;
import tubs.cs.satencodings.generation.DefaultVariableFactory;
import tubs.cs.satencodings.generation.encodings.*;
import tubs.cs.satencodings.generation.encodings.combined.SelectiveEncoding;
import tubs.cs.satencodings.generation.featuremodel.AtMostFMGenerator;
import tubs.cs.satencodings.util.AnnotatedNode;
import tubs.cs.satencodings.util.SimpleFileWriter;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;

import de.ovgu.featureide.fm.core.base.IFeatureModel;

public class Main {
	public static void main(String[] args) {
		final List<Literal> variables = Stream.of(
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
				).map(varName -> new Literal(varName)).toList();

		final int numberOfVariables = variables.size();
		final int numberOfVariablesToSelectAtMost = 3;
		
		System.out.println("Number of variables: " + numberOfVariables);
		System.out.println("Number of variables to select at most: " + numberOfVariablesToSelectAtMost);
		
		final EncodingOptions encodingOptions = new EncodingOptions(
				true, ""
		);
		
		final AtMostSATEncoding encoding =
				new SelectiveEncoding()
		//		new BinomialEncoding()
		//		new CommanderEncoding()
		//		new BinaryEncoding()
		//		new BinaryCNFEncoding()
		//		new SequentialCounterEncoding()
				;
		
		
		// Generate a formula that encodes atMost(variables, numberOfVariablesToSelectAtMost).
		{
			encoding.setVariableFactory(new DefaultVariableFactory()); // factory for generatng variables
			final List<AnnotatedNode> formulaWithMetadata = encoding.encodeAtMost(variables, numberOfVariablesToSelectAtMost, encodingOptions);
			final Node atMost = new And(formulaWithMetadata.stream().map(a -> a.node).toArray());
		}

		// Generate a feature model that encodes atMost(variables, numberOfVariablesToSelectAtMost).
		{
			final AtMostFMGenerator featureModelGenerator = new AtMostFMGenerator();
			final IFeatureModel fm = featureModelGenerator.generateAtMost(
					numberOfVariables,
					numberOfVariablesToSelectAtMost,
					encoding,
					encodingOptions); // This will also overwrite the factory in the encoding.
		
			// Write feature model
			SimpleFileWriter writer = new SimpleFileWriter();
			writer.writeFile(encoding.getName() + "_atmost_" + numberOfVariablesToSelectAtMost + "_in_" + numberOfVariables + ".xml", fm, false);
		}
	}
}
