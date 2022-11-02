package core.basesyntax.strategy.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class SupplyOperationHandlerImpl implements OperationHandler {
    private final StorageDao storageDao;

    public SupplyOperationHandlerImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public void changeQuantity(FruitTransaction fruitTransaction) {
        Integer fruitQuantity = storageDao.getFruitBalance(fruitTransaction.getFruit());
        if (fruitQuantity == null) {
            storageDao.getStorage().put(fruitTransaction.getFruit(),
                    fruitTransaction.getQuantity());
        } else {
            storageDao.updateData(fruitTransaction.getFruit(),
                    storageDao.getFruitBalance(fruitTransaction.getFruit())
                            + fruitTransaction.getQuantity());
        }
    }
}