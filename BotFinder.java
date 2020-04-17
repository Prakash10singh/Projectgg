import java.util.*;

public class BotFinder {

    Map<String,String> findingbot;

    /**
     * Create a bot finder with a set of accounts and tweets
     * @param accounts the list of account names
     * @param tweets List of each all tweets per account. tweets.get(i) contains
     *               all tweets for the account at accounts.get(i)
     */
    public BotFinder(List<String> accounts, List<String> tweets) {
        findingbot = new HashMap<>();
        for (int i = 0 ; i < accounts.size();i++){
           findingbot.put(accounts.get(i) ,tweets.get(i) );
       }

        for(Map.Entry m:findingbot.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }

    /**
     * Find the frequency of use of a specific word by a specific account.
     * @param accountName name of account
     * @param word word to find the frequency of
     * @return the frequency of use for the word by the account
     */
    public double getAccountWordFrequency(String accountName, String word) {

    double result=0.0;

    if(findingbot.containsKey(accountName)){
        String valueFromKey  = findingbot.get(accountName);
        String []stringWords = valueFromKey.split("\\s+");

        for (int i = 0; i <stringWords.length ; i++) {
            if(cleanString(stringWords[i]).matches("\\b"+word+"\\b"))
            {
                result +=1.0;
            }
        }
        result = result/stringWords.length;
    }
        return result;
    }

    /**
     * Find the similarity of two accounts based off their word use frequency
     * @param accountOne first account to analyze
     * @param accountTwo second account to analyze
     * @return a similarity score between -1 and 1, representing the account similarity
     */
    public double getAccountSimilarity(String accountOne, String accountTwo) {
      double count = 0.0;
            String valueFromAccountOne  = findingbot.get(accountOne);
            String[] s1 = valueFromAccountOne.split("\\s+");

        for (int i = 0; i < s1.length; i++) {
            String s2 = cleanString(s1[i]);
            count += (getAccountWordFrequency(accountOne, s2) * getAccountWordFrequency(accountTwo, s2));
        }

    return count;
    }
    /**
     * Cleans a given string, removing punctuation and converting to lower case
     * @param rawString raw string
     * @return cleaned string
     */
    private static String cleanString(String rawString) {
        return rawString.replaceAll("\\W", "").toLowerCase();
    }
}
