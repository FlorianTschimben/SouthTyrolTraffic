import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetInformation {
	public static NodeList items = null;

	public static String[] getCategories() {
		Set<String> categoriesSet = new HashSet<>();
		try {
			URL url = new URL("https://verkehr.provinz.bz.it/rss.xml");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream inputStream = connection.getInputStream();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			NodeList items = document.getElementsByTagName("item");
			GetInformation.items = items;
			for (int i = 0; i < items.getLength(); i++) {
				String category = items.item(i).getChildNodes().item(4).getTextContent();
				categoriesSet.add(category);
			}
		} catch (Exception e) {
			return new String[] { "Failed to fetch updates: " + e.getMessage() };
		}
		return categoriesSet.toArray(new String[0]);
	}

	public static String[] getInformationByCategoryName(String categoryName) {
		List<String> results = new ArrayList<>();
		if (items == null) {
			return new String[] { "No items available. Please fetch the updates first." };
		}
		boolean viewAll = false;
		if (categoryName.equals("Alle Informationen"))
			viewAll = true;
		for (int i = 0; i < items.getLength(); i++) {
			Node item = items.item(i);
			String category = item.getChildNodes().item(4).getTextContent();
			if (category.equals(categoryName) || viewAll) {
				String title = item.getChildNodes().item(0).getTextContent();
				String description = item.getChildNodes().item(3).getTextContent();
				results.add("Title: " + title + "\nDescription:\n" + description);
			}
		}
		return results.toArray(new String[0]);
	}
}
