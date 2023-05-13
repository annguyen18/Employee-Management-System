package com.annguyen18.EmployeeManagementSystem;

import com.annguyen18.EmployeeManagementSystem.models.Department;
import com.annguyen18.EmployeeManagementSystem.models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
        int id = 0;
        if (object instanceof Employee) {
            id = Integer.parseInt("" + 101 + randomNum);
        } if (object instanceof Department) {
            id = Integer.parseInt("" + 102 + randomNum);
        }
        return id;
    }
}
