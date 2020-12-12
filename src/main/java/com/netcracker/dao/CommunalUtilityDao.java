package com.netcracker.dao;

import com.netcracker.models.CommunalUtility;

import java.math.BigInteger;
import java.util.List;

public interface CommunalUtilityDao {

    //getAllCommunalUtilitiesWithCalculationMethod using OBJREFERENCE
    String getAllCommunalUtilitiesWithCalculationMethod = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "calc_obj.OBJECT_ID calc_id,\n" +
            "calc_name.value calc_name,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype,\n" +
            "    OBJECTS calc_obj,\n" +
            "    ATTRIBUTES calc_name,\n" +
            "    OBJREFERENCE objref\n" +
            "    where\n" +
            "    com_util_name.attr_id=21\n" +
            "    and com_util_obj.OBJECT_ID = objref.OBJECT_ID\n" +
            "    and calc_obj.OBJECT_ID = objref.REFERENCE\n" +
            "    and objref.attr_id = 39\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status_list.attr_id=23\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype_list.attr_id=22\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n" +
            "    and calc_name.attr_id=20\n" +
            "    and calc_name.object_id=calc_obj.object_id\n";

    String getAllCommunalUtilities = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_name.attr_id=21\n" +
            "    and com_util_obj.OBJECT_TYPE_ID=11\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status_list.attr_id=23\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype_list.attr_id=22\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    //getCommunalUtilityByIdWithCalculationMethod with OBJREFERENCE
    String getCommunalUtilityWithCalculationMethodById = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "calc_obj.OBJECT_ID calc_id,\n" +
            "calc_name.value calc_name,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype,\n" +
            "    OBJECTS calc_obj,\n" +
            "    ATTRIBUTES calc_name,\n" +
            "    OBJREFERENCE objref\n" +
            "    where\n" +
            "    com_util_obj.OBJECT_ID = ?\n" +
            "    and com_util_obj.OBJECT_ID = objref.OBJECT_ID\n" +
            "    and calc_obj.OBJECT_ID = objref.REFERENCE\n" +
            "    and objref.attr_id = 39\n" +
            "    and com_util_name.attr_id=21\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status_list.attr_id=23\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype_list.attr_id=22\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n" +
            "    and calc_name.attr_id=20\n" +
            "    and calc_name.object_id=calc_obj.object_id";

    String getCommunalUtilityById = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_obj.OBJECT_ID = ?\n" +
            "    and com_util_name.attr_id=21\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status_list.attr_id=23\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype_list.attr_id=22\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    String createCommunalUtility = "insert all\n" +
            "into OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)\n" +
            "VALUES (OBJ_ID_SEQ.nextval ,? ,11 , null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (21 ,OBJ_ID_SEQ.currval,? ,null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (22 ,OBJ_ID_SEQ.currval,null,null,? )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (23 ,OBJ_ID_SEQ.currval,null,null,? )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (24 ,OBJ_ID_SEQ.currval,null,?,null )\n" +
            "into OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE)\n" +
            "values (39 ,OBJ_ID_SEQ.currval, ?)\n" +
            "select * from dual";

    String updateCommunalUtility = "merge into ATTRIBUTES x\n" +
            "using (SELECT 21 ATTR_ID, ? VALUE,null DATE_VALUE, null list_value_id from dual\n" +
            "       union all\n" +
            "       select 22 ATTR_ID, null, null, ?  from DUAL\n" +
            "       union all\n" +
            "       select 23 ATTR_ID, null, null,? from DUAL\n" +
            "       union all\n" +
            "       select 24 ATTR_ID, null, ?, null from DUAL\n" +
            ") y\n" +
            "on (x.OBJECT_ID = ? and x.ATTR_ID = y.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE SET x.VALUE = y.VALUE,\n" +
            "               x.DATE_VALUE = y.DATE_VALUE,\n" +
            "               x.list_value_id = y.list_value_id";

    String updateCommunalUtilityReference = "merge into OBJREFERENCE x\n" +
            "using (SELECT 39 ATTR_ID,? REFERENCE,? OBJECT_ID from DUAL) y\n" +
            "on ( x.ATTR_ID=y.ATTR_ID AND x.OBJECT_ID=y.OBJECT_ID)\n" +
            "when matched then \n" +
            "update set x.REFERENCE=y.REFERENCE\n" +
            "when not matched then \n" +
            "insert (x.REFERENCE,x.OBJECT_ID,x.ATTR_ID)\n" +
            "values (y.REFERENCE,y.OBJECT_ID,y.ATTR_ID)";

    String EXCEPTION_GET_ALL_COMMUNAL_UTILITIES = "Can't get communal utilities";
    String EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID = "Can't get communal utility by id";
    String EXCEPTION_UPDATE_COMMUNAL_UTILITY = "Can't update communal utility";
    String EXCEPTION_CREATE_COMMUNAL_UTILITIES = "Can't create communal utility";

    List<CommunalUtility> getAllCommunalUtilities();

    List<CommunalUtility> getAllCommunalUtilitiesWithCalculationMethod();

    CommunalUtility getCommunalUtilityById(BigInteger id);

    CommunalUtility getCommunalUtilityByIdWithCalculationMethod(BigInteger id);

    void updateCommunalUtility(CommunalUtility communalUtility);

    void createCommunalUtility(CommunalUtility communalUtility);
}
