package ru.ponkin.billing.services;


import static org.junit.Assert.*;
import org.junit.Test;
import org.easymock.*;
import ru.ponkin.billing.dao.GenericDAO;
import ru.ponkin.billing.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Alexey Ponkin
 */
public class TransferServiceTest {

  @Test
  @SuppressWarnings("unchecked")
  public void testTransfer() throws Exception {
    GenericDAO<Account> accountDao  = EasyMock.createMock(GenericDAO.class);
    TransferService service = new TransferService(accountDao);
    Account a1 = new Account();
    a1.setId(1L);
    a1.setAmount(new BigDecimal(100));

    Account a2 = new Account();
    a2.setId(2L);
    a2.setAmount(new BigDecimal(50));

    EasyMock.expect(accountDao.findByIdAndLock(EasyMock.eq(1L))).andReturn(Optional.of(a1));
    EasyMock.expect(accountDao.findByIdAndLock(EasyMock.eq(2L))).andReturn(Optional.of(a2));
    EasyMock.expect(accountDao.update(EasyMock.eq(a1))).andReturn(a1);
    EasyMock.expect(accountDao.update(EasyMock.eq(a2))).andReturn(a2);
    EasyMock.replay(accountDao);
    service.transfer(1L, 2L, new BigDecimal(100));
    assertEquals(a1.getAmount(), BigDecimal.ZERO);
    assertEquals(a2.getAmount(), new BigDecimal(150));
    EasyMock.verify(accountDao);
  }
}
