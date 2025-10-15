package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.bitrix24.pageobject.enums.DealsStatus;

public class DealFrameTests extends TestBase {

    @ParameterizedTest
    @EnumSource(DealsStatus.class)
    public void AllStagesOfDealDisplayedInDealIframe(){

    }
}
