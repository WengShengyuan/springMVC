package org.company.core.moduel.dao;

import org.company.core.moduel.domain.TestTable;
import org.company.frame.generic.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;
@Repository("TestTableDaoImpl")
public class TestTableDaoImpl extends GenericDaoImpl<TestTable> implements TestTableDao{

}
