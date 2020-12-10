package com.netcracker.dao;

import com.netcracker.models.CalculationMethod;

import java.math.BigInteger;
import java.util.List;

public interface CalculationMethodDao {

    String createCalculationMethodObject = "merge into OBJECTS x\n" +
            "    using (select seq_obj_next OBJECT_ID,10 OBJECT_TYPE_ID,'CalculationMethod' || seq_obj_curr NAME\n" +
            "from DUAL) y\n" +
            "    on ( x.OBJECT_ID=y.OBJECT_ID )\n" +
            "when matched then\n" +
            "    update set x.PARENT_ID=null,x.NAME=y.NAME,x.OBJECT_TYPE_ID=y.OBJECT_TYPE_ID,x.DESCRIPTION=null\n" +
            "when not matched then\n" +
            "    insert (x.OBJECT_ID,x.OBJECT_TYPE_ID,x.NAME)\n" +
            "    values ( y.OBJECT_ID,y.OBJECT_TYPE_ID,y.NAME)";

    String createCalculationMethodAttributes = "merge into ATTRIBUTES x\n" +
            "using (SELECT 20 ATTR_ID, ? VALUE,seq_obj_curr OBJECT_ID from dual ) y\n" +
            "on (x.OBJECT_ID = y.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET x.VALUE = y.VALUE\n" +
            "when not matched then\n" +
            "insert (x.ATTR_ID,x.VALUE,x.OBJECT_ID)\n" +
            "VALUES ( y.ATTR_ID,y.VALUE, y.OBJECT_ID )\n";

    String updateCalculationMethod = "merge into ATTRIBUTES x\n" +
            "using (SELECT 20 ATTR_ID, ? VALUE,? OBJECT_ID from dual ) y\n" +
            "on (x.OBJECT_ID = y.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET x.VALUE = y.VALUE\n" +
            "when not matched then \n" +
            "insert (x.ATTR_ID,x.VALUE,x.OBJECT_ID)\n" +
            "VALUES ( y.ATTR_ID,y.VALUE,y.OBJECT_ID)";

    String deleteCalculationMethod = "delete from OBJECTS\n" +
            "where OBJECT_ID = ?";

    String getCalculationMethodById = "select calcname.OBJECT_ID calc_id, calcname.VALUE calc_name from\n" +
            "ATTRIBUTES calcname\n" +
            "where calcname.OBJECT_ID =  ? AND calcname.ATTR_ID = 20";

    String getCalculationMethodByCommunalUtilityId = "select calcname.OBJECT_ID calc_id, " +
            "calcname.VALUE calc_name from\n" +
            "ATTRIBUTES calcname,\n" +
            "OBJREFERENCE objref\n" +
            "where calcname.OBJECT_ID =  objref.REFERENCE " +
            "AND calcname.ATTR_ID = 20" +
            "AND objref.OBJECT_ID = ?";

    String getAllCalculationMethods = "select calcname.OBJECT_ID calc_id, calcname.VALUE calc_name from\n" +
            "ATTRIBUTES calcname\n" +
            "where calcname.ATTR_ID = 20";

    List<CalculationMethod> getAllCalculationMethods();

    CalculationMethod getCalculationMethodById(BigInteger id);

    CalculationMethod getCalculationMethodByCommunalUtilityId(BigInteger id);

    void updateCalculationMethod(CalculationMethod calculationMethod);

    void createCalculationMethod(CalculationMethod calculationMethod);

    void deleteCalculationMethod(BigInteger id);
}
