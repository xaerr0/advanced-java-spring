package platform.codingnomads.co.corespring.examples.annotations.whyandhow;


public class CodeWarriorService implements LegacyInfoProvider {

    @CodeWarriorInfo
    @Override
    public String codeWarriorInfo() {
        return "legacy api fetching code warrior info";
    }
}
