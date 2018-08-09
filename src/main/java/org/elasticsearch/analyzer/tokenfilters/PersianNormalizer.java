package org.elasticsearch.analyzer.tokenfilters;

/**
 * Created by Nariman on 10/29/2017.
 */
public class PersianNormalizer {

    public static final char ALEF = '\u0627';
    public static final char ALEF_MADDA = '\u0622';
    public static final char ALEF_HAMZA_ABOVE = '\u0623';
    public static final char ALEF_HAMZA_BELOW = '\u0625';

    public static final char WAW = '\u0648';
    public static final char WAW_HAMZA_ABOVE = '\u0624';

    public static final char YEH = '\u064A';
    public static final char YEH_HAMZA_ABOVE = '\u0626';
    public static final char FARSI_YEH = '\u06CC';

    public static final char KAF = '\u0643';
    public static final char KEHEH = '\u06A9';

    public static final char HEH = '\u0647';
    public static final char HEH_HAMZA_ABOVE = '\u06C2';
    public static final char TEH_MARBUTA = '\u0629';

    public static final char KASHIDA = '\u0640';

    public static final char HAMZA = '\u0621';

    public static final char FATHATAN = '\u064B';
    public static final char DAMMATAN = '\u064C';
    public static final char KASRATAN = '\u064D';
    public static final char FATHA = '\u064E';
    public static final char DAMMA = '\u064F';
    public static final char KASRA = '\u0650';
    public static final char SHADDA = '\u0651';
    public static final char SUKUN = '\u0652';

    public String normalize(String text) {

        StringBuilder stringBuilder = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case ALEF_MADDA:
                case ALEF_HAMZA_ABOVE:
                case ALEF_HAMZA_BELOW:
                    stringBuilder.append(ALEF);
                    break;

                case WAW_HAMZA_ABOVE:
                    stringBuilder.append(WAW);
                    break;

                case YEH:
                    stringBuilder.append(FARSI_YEH);
                    break;

                case YEH_HAMZA_ABOVE:
                    stringBuilder.append(FARSI_YEH);
                    break;

                case KAF:
                    stringBuilder.append(KEHEH);
                    break;

                case TEH_MARBUTA:
                    stringBuilder.append(HEH);
                    break;

                case HEH_HAMZA_ABOVE:
                    stringBuilder.append(HEH);
                    break;

                case KASHIDA:
                case KASRATAN:
                case DAMMATAN:
                case FATHATAN:
                case FATHA:
                case DAMMA:
                case KASRA:
                case SHADDA:
                case SUKUN:
                case HAMZA:
                    //Delete these characters
                    break;

                default:
                    stringBuilder.append(text.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}