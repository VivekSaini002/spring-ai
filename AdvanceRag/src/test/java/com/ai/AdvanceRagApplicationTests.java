package com.ai;

import com.ai.service.DataLoader;
import com.ai.service.DataTransformer;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdvanceRagApplicationTests {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private DataTransformer dataTransformer;

	@Autowired
	private VectorStore vectorStore;

	@Test
	void testJsonDataLoader() {
		System.out.println("Test");
		var document = dataLoader.loadDocumentsFromJson();
		System.out.println(document.size());
		document.forEach(items ->
				System.out.println(items)
		);

	}

	@Test
	void testPdfDataLoader() {
		System.out.println("Test");
		List<Document> documents = this.dataLoader.loadDocumentsFromPdf();
		System.out.println(documents.size());
		documents.forEach(items -> {
			System.out.println(items);
			System.out.println("------------------------------");
		});
		System.out.println("Read now going to transform");
		var transformDocument = this.dataTransformer.transform(documents);
		System.out.println(transformDocument.size());

//		saving the data in vector database
		this.vectorStore.add(transformDocument);
		System.out.println("Done. Save successfully...");


	}

}
