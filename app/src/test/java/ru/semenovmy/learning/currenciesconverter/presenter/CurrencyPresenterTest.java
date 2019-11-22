package ru.semenovmy.learning.currenciesconverter.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.semenovmy.learning.currenciesconverter.data.ConversionRate;
import ru.semenovmy.learning.currenciesconverter.data.CurrencyConverter;
import ru.semenovmy.learning.currenciesconverter.data.CurrencyRepository;
import ru.semenovmy.learning.currenciesconverter.data.model.Currency;
import ru.semenovmy.learning.currenciesconverter.view.ICurrencyView;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyPresenterTest {

    private CurrencyRepository mCurrencyRepository;
    private ICurrencyView mICurrencyView;
    private CurrencyConverter mCurrencyConverter;
    private ConversionRate mConversionRate;
    private WeakReference<ICurrencyView> mWeakReference;
    private CurrencyPresenter mMainPresenter;

    /**
     * Данный метод будет вызван перед каждым тестовым методом.
     */
    @Before
    public void setUp() {
        mWeakReference = new WeakReference<>(mICurrencyView);
        mMainPresenter = new CurrencyPresenter(mICurrencyView);
        mCurrencyRepository = Mockito.spy(new CurrencyRepository());
    }

    /**
     * Тестирование асинхронного метода получения данных в презентере.
     */
    @Test
    public void testLoadDataAsync() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                CurrencyRepository.OnLoadingFinishListener onLoadingFinishListener =
                        (CurrencyRepository.OnLoadingFinishListener) invocation.getArguments()[1];

                onLoadingFinishListener.onFinish(createTestData());

                return null;
            }
        }).when(mCurrencyRepository).loadDataAsync(Mockito.any(CurrencyRepository.OnLoadingFinishListener.class));

        mCurrencyRepository.loadDataAsync(Mockito.any(CurrencyRepository.OnLoadingFinishListener.class));
    }

    private List<Currency> createTestData() {
        List<Currency> testData = new ArrayList<>();

        testData.add(new Currency("0", "Sberbank", 100, null, new BigDecimal("45.678")));
        testData.add(new Currency("1", "Sberbank1", 101, null, new BigDecimal("345.678")));

        return testData;
    }

}