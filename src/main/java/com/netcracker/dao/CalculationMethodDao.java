package com.netcracker.dao;

import com.netcracker.models.CalculationMethod;

import java.math.BigInteger;
import java.util.List;

public interface CalculationMethodDao {
     String createCalculationMethodAttributes = "insert all\n" +
            "into OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)\n" +
            "VALUES (OBJ_ID_SEQ.nextval ,null ,10 , null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (20 ,OBJ_ID_SEQ.currval,? ,null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (40 ,OBJ_ID_SEQ.currval,?,null,null )\n" +
            "select * from dual";

    String updateCalculationMethod = "merge into ATTRIBUTES x\n" +
            "using (SELECT 20 ATTR_ID, ? VALUE from dual ) y\n" +
            "union all\n" +
            "SELECT 40, ?  from dual) y\n" +
            "on (x.OBJECT_ID = ? and x.ATTR_ID = y.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET x.VALUE = y.VALUE";

    String deleteCalculationMethod = "delete from OBJECTS\n" +
            "where OBJECT_ID = ?";

    String getCalculationMethodById = "select calcname.OBJECT_ID calc_id, calcname.VALUE calc_name, calc_coeff.VALUE calc_coeff from\n" +
            "ATTRIBUTES calcname\n" +
            "ATTRIBUTES calc_coeff,\n" +
            "OBJECTS calc_obj,\n" +
            "where\n" +
            "calcname.attr_id=20\n" +
            "and calc_obj.OBJECT_TYPE_ID=10\n" +
            "and calcname.object_id = calc_obj.OBJECT_ID\n" +
            "and calc_obj.object_id = ?\n" +
            "and calc_coeff.attr_id = 40\n" +
            "and calc_obj.OBJECT_TYPE_ID = 10\n" +
            "and calc_coeff.object_id = calc_obj.OBJECT_ID";

    String getCalculationMethodByCommunalUtilityId = "select calcname.OBJECT_ID calc_id, " +
            "calcname.VALUE calc_name, calc_coeff.VALUE calc_coeff from\n" +
            "ATTRIBUTES calcname,\n" +
            "OBJREFERENCE objref,\n" +
            "ATTRIBUTES calc_coeff,\n" +
            "OBJECTS calc_obj\n" +
            "where\n" +
            "calcname.attr_id = 20\n" +
            "and calc_obj.OBJECT_TYPE_ID = 10\n" +
            "and calcname.object_id = calc_obj.OBJECT_ID\n" +
            "and objref.reference = calc_obj.OBJECT_ID\n" +
            "and objref.object_id = ?\n" +
            "and objref.attr_id = 39\n" +
            "and calc_coeff.attr_id = 40\n" +
            "and calc_obj.OBJECT_TYPE_ID = 10\n" +
            "and calc_coeff.object_id = calc_obj.OBJECT_ID";

    String getAllCalculationMethods = "select calcname.OBJECT_ID calc_id, calcname.VALUE calc_name, calc_coeff.VALUE calc_coeff from\n" +
            "    ATTRIBUTES calcname,\n" +
            "    ATTRIBUTES calc_coeff,\n" +
            "            OBJECTS calc_obj\n" +
            "where\n" +
            "    calcname.attr_id=20\n" +
            "  and calc_obj.OBJECT_TYPE_ID=10\n" +
            "  and calcname.object_id = calc_obj.OBJECT_ID\n" +
            "  and calc_coeff.attr_id=40\n" +
            "  and calc_obj.OBJECT_TYPE_ID=10\n" +
            "  and calc_coeff.object_id = calc_obj.OBJECT_ID";


    String EXCEPTION_GET_ALL_CALCULATION_METHODS = "Can't get calculation methods";
    String EXCEPTION_DELETE_CALCULATION_METHODS_ = "Can't delete calculation method with id:";
    String EXCEPTION_GET_CALCULATION_METHOD_BY_ID = "Can't get calculation method with id:";
    String EXCEPTION_GET_CALCULATION_METHOD_BY_COMMUNAL_UTILITY_ID = "Can't get calculation method with communal utility id:";
    String EXCEPTION_UPDATE_CALCULATION_METHOD = "Can't update calculation method with id:";
    String EXCEPTION_CREATE_CALCULATION_METHOD = "Can't create calculation method with id:";

    List<CalculationMethod> getAllCalculationMethods();

    CalculationMethod getCalculationMethodById(BigInteger id);

    CalculationMethod getCalculationMethodByCommunalUtilityId(BigInteger id);

    void updateCalculationMethod(CalculationMethod calculationMethod);

    void createCalculationMethod(CalculationMethod calculationMethod);

    void deleteCalculationMethod(BigInteger id);
}
