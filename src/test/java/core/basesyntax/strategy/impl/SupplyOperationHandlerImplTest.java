package core.basesyntax.strategy.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SupplyOperationHandlerImplTest {
    private static final String FRUIT_TYPE = "banana";
    private OperationHandler operationHandler;
    private StorageDao storageDao;

    @Before
    public void setUp() {
        storageDao = new StorageDaoImpl();
        operationHandler = new SupplyOperationHandlerImpl(storageDao);
    }

    @Test
    public void supply_Operation_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.SUPPLY,
                FRUIT_TYPE,50);
        storageDao.getStorage().put(FRUIT_TYPE,50);
        operationHandler.changeQuantity(fruitTransaction);
        Integer expected = 100;
        Integer actual = storageDao.getFruitBalance(FRUIT_TYPE);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void supply_Operation_New_Fruit_Ok() {
        FruitTransaction fruitTransaction = new FruitTransaction(FruitTransaction.Operation.SUPPLY,
                FRUIT_TYPE,50);
        operationHandler.changeQuantity(fruitTransaction);
        Integer expected = 50;
        Integer actual = storageDao.getFruitBalance(FRUIT_TYPE);
        Assert.assertEquals(expected,actual);
    }

    @After
    public void tearDown() {
        storageDao.getStorage().clear();
    }
}