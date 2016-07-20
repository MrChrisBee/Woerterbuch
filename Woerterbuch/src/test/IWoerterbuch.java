package test;

import java.util.NavigableSet;

public interface IWoerterbuch {

	public abstract String getSrcLanguage();

	public abstract String getDstLanguage();

	public abstract boolean putWord(String srcWord, String dstWord);

	public abstract boolean putWords(String srcWord, String dstWord, String... dstWords);

	public abstract boolean updateWord(String srcWord, String dstOldWord, String dstNewWord);

	public abstract NavigableSet<String> getWords(String srcWord);

	public abstract boolean removeWord(String srcWord, String dstWord);

	public abstract boolean removeEntry(String srcWord);

	public abstract IWoerterbuch invertDict();

}