import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleTextReader {
    private static final String[] EASY_SAMPLE_TEXTS = {
        "Typing is an essential skill in the modern world. Whether you are sending emails, writing reports, or" +
                " chatting with friends, the ability to type quickly and accurately saves time. The best way to" +
                " improve your typing speed is through regular practice. Start with simple sentences, focusing on" +
                " accuracy before speed. Gradually, as your fingers become more familiar with the keyboard, you" +
                " will notice an increase in speed. Try to keep your posture straight and hands relaxed while" +
                " typing. Avoid looking at the keyboard and rely on muscle memory instead. Over time, your typing" +
                " will become smooth and effortless. Keep practicing, and soon you will be typing at an " +
                "impressive speed with minimal errors.",
        "On a sunny day, the park is a great place to relax. Many people walk their dogs, children play on swings," +
                " and families have picnics. The trees provide shade, and the grass is soft underfoot. Some people" +
                " like to sit on benches, reading books or chatting with friends. The sound of birds chirping adds" +
                " to the peaceful atmosphere. There are also small ponds where ducks swim, and sometimes, you can see" +
                " children feeding them. Everyone enjoys the fresh air and the opportunity to spend time outside.",
        "My favorite food is pizza. I love the crispy crust, melted cheese, and the variety of toppings. Sometimes I" +
                " enjoy pizza with just cheese and tomato sauce, while other times I add vegetables or meat. I " +
                "usually order pizza on weekends when I’m feeling hungry for something tasty and filling. It’s a fun" +
                " food to share with friends or family. Everyone can pick their favorite toppings, and we can all" +
                " enjoy it together.",
        "There are four seasons in a year: spring, summer, fall, and winter. In spring, flowers bloom, and the" +
                " weather starts to warm up. Summer is hot, and people often go to the beach or have barbecues." +
                " In fall, the leaves change color, and the weather becomes cooler. Winter is cold, and sometimes " +
                "it snows. Each season has its own special qualities, and it’s fun to enjoy the changes in nature " +
                "throughout the year."
    };
    private static final String[] INTERMEDIATE_SAMPLE_TEXTS = {
        "Last summer, I went on a road trip with my friends. We drove to the mountains, excited to explore the" +
                " outdoors. The journey was long, but the scenery was beautiful. We passed by forests, rivers," +
                " and small towns. When we reached our destination, we went hiking and spent time enjoying the" +
                " fresh air. At night, we sat around a campfire, telling stories and roasting marshmallows. The" +
                " stars in the sky were brighter than I had ever seen before. It was a peaceful and unforgettable" +
                " experience. We didn’t have to worry about the usual hustle and bustle of daily life, and it felt" +
                " great to reconnect with nature.",
        "Reading is a great way to expand your knowledge and improve your vocabulary. Whether you enjoy fiction or" +
                " non-fiction, books provide a window into different worlds and ideas. Reading also helps to" +
                " improve concentration and focus, as it requires you to pay attention to details and follow a" +
                " story or argument. In addition to its educational benefits, reading can also be a relaxing" +
                " hobby. After a busy day, curling up with a good book can be a great way to unwind and escape" +
                " into a new world.",
        "Technology is advancing rapidly, and it is changing the way we live. In the future, we may see even more" +
                " impressive innovations, such as self-driving cars, advanced artificial intelligence, and new" +
                " ways to communicate. These developments could make life more convenient and efficient. However" +
                ", there are also concerns about the impact of technology on jobs and privacy. As technology" +
                " continues to evolve, it’s important to find a balance between progress and ensuring that " +
                "everyone benefits from these changes."
    };
    private static final String[] DIFFICULT_SAMPLE_TEXTS = {
        "In recent years, sustainability has become a key factor in the success of many modern businesses." +
                " Companies are increasingly adopting eco-friendly practices, both in their products and" +
                " their operations, as they recognize the importance of protecting the environment." +
                " Sustainability not only helps to preserve natural resources but also appeals to " +
                "consumers who are more conscious of their environmental impact. Businesses that prioritize" +
                " sustainability often gain a competitive edge, as consumers are willing to support companies" +
                " that align with their values. This trend has led to innovations such as reusable packaging," +
                " renewable energy usage, and waste reduction strategies. However, implementing sustainable" +
                " practices often requires significant investment and changes in business processes." +
                " In addition, measuring the long-term benefits of sustainability can be challenging," +
                " and it requires careful planning and commitment. Despite these challenges, many " +
                "businesses are embracing sustainability as an essential part of their strategy, " +
                "understanding that it is crucial for both their success and the future of the planet.",
        "Globalization has had a profound effect on cultures around the world. As technology and communication " +
                "improve, people from different countries are more connected than ever before. While this has " +
                "led to greater exchange of ideas, goods, and services, it has also raised concerns about the " +
                "erosion of cultural identity. In many places, traditional customs and languages are being " +
                "overshadowed by global influences, such as Western media and international corporations. " +
                "This has led to debates about the preservation of local cultures in the face of " +
                "globalization. On the one hand, globalization can foster greater understanding and " +
                "cooperation between cultures. On the other hand, it can lead to the homogenization " +
                "of cultures, where local traditions are lost or diluted. As a result, there is a growing" +
                " movement to protect and celebrate cultural diversity, ensuring that globalization does" +
                " not lead to the disappearance of unique cultural identities.",
        "Artificial intelligence (AI) has the potential to revolutionize many aspects of our lives, from " +
                "healthcare to transportation. However, with these advancements come ethical concerns " +
                "that need to be addressed. One of the main issues is the impact of AI on employment. " +
                "As AI systems become more capable, they may replace jobs currently performed by humans," +
                " leading to unemployment and economic inequality. Another concern is the potential for " +
                "AI to be used in ways that violate privacy or human rights. For example, AI-powered " +
                "surveillance systems could be used to monitor individuals without their consent, raising " +
                "questions about the balance between security and freedom. Additionally, there are " +
                "concerns about bias in AI algorithms, which could result in unfair treatment of certain" +
                " groups. As AI continues to develop, it is essential that ethical guidelines are established" +
                " to ensure that its use benefits society as a whole and does not cause harm.rf"
    };
    private static final int NUM_SAMPLE_TEXTS = 3;

    public SampleTextReader() {
    }

    public static List<String> readSampleText(String difficultyLevel) {
        List<String> lines = new ArrayList<>();
        String[] texts;
        switch (difficultyLevel) {
        case "easy":
            texts = EASY_SAMPLE_TEXTS;
            break;
        case "intermediate":
            texts = INTERMEDIATE_SAMPLE_TEXTS;
            break;
        case "difficult":
            texts = DIFFICULT_SAMPLE_TEXTS;
            break;
        default:
            throw new IllegalArgumentException("Invalid difficulty level.");
        }

        // choose random text
        Random random = new Random();
        int randomIndex = random.nextInt(NUM_SAMPLE_TEXTS);
        String randomText = texts[randomIndex];
        // split the text into lines
        String[] textLines = randomText.split("\\.");
        for (String line : textLines) {
            if (!line.trim().isEmpty()) {
                lines.add(line.trim() + ".");
            } else {
                lines.add(line.trim());
            }
        }
        return lines;
    }
}
