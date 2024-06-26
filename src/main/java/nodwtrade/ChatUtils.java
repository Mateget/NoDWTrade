package nodwtrade;

import java.util.List;

import com.google.common.collect.Lists;


public class ChatUtils {

	private static final char COLOUR_CHAR = '\u00a7';
    private static final String CHARACTERS = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";

    /**
     *
     * Translates the text to coloured text.
     * Reference: https://hub.spigotmc.org/stash/projects/SPIGOT/repos/bukkit/browse/src/main/java/org/bukkit/ChatColor.java#216
     *
     * @param altColorChar The character
     * @param textToTranslate The text
     * @return The coloured text
     */
    public static String translateColourCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && CHARACTERS.indexOf(b[i + 1]) > -1) {
                b[i] = COLOUR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    /**
     *
     * Translates each line in the list to coloured text.
     * Using {@link UtilChatColour#translateColourCodes(char, String)}
     *
     * @param altColorChar The character
     * @return The coloured text
     */
    
    public static List<String> translateColourCodes(List<String> lines) {
        List<String> newLines = Lists.newArrayList();

        for (String line : lines) {
            newLines.add(translateColourCodes('&', line));
        }

        return newLines;
    }
	
}
