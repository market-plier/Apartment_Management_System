package com.netcracker.dao;

import com.netcracker.models.CommunalUtility;

import java.math.BigInteger;
import java.util.List;

public interface CommunalUtilityDao {

    String getAllCommunalUtilities = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_coeff.VALUE com_util_coeff,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_calc_list.VALUE com_util_calc,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    ATTRIBUTES com_util_coeff,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_calc_list,\n" +
            "    ATTRIBUTES com_util_calc,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_name.attr_id=21\n" +
            "    and com_util_obj.OBJECT_TYPE_ID=11\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_coeff.attr_id=40\n" +
            "    and com_util_coeff.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_calc.attr_id=20\n" +
            "    and com_util_calc.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_calc.list_value_id = com_util_calc_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    String getAllCommunalUtilitiesFilterByStatus = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_calc_list.VALUE com_util_calc,\n" +
            "com_util_coeff.VALUE com_util_coeff,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_calc_list,\n" +
            "    ATTRIBUTES com_util_coeff,\n" +
            "    ATTRIBUTES com_util_calc,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_name.attr_id=21\n" +
            "    and com_util_coeff.attr_id=40\n" +
            "    and com_util_coeff.object_id = com_util_obj.OBJECT_ID\n" +
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
            "    and com_util_calc.attr_id=20\n" +
            "    and com_util_calc.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_calc.list_value_id = com_util_calc_list.list_value_id\n" +
            "    and com_util_status.list_value_id = ?\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    String getCommunalUtilityById = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_coeff.VALUE com_util_coeff,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_calc_list.VALUE com_util_calc,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    ATTRIBUTES com_util_coeff,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_calc_list,\n" +
            "    ATTRIBUTES com_util_calc,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_obj.OBJECT_ID=?\n" +
            "    and com_util_name.attr_id=21\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_coeff.attr_id=40\n" +
            "    and com_util_coeff.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_calc.attr_id=20\n" +
            "    and com_util_calc.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_calc.list_value_id = com_util_calc_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    String getCommunalUtilityUnique = "select com_util_obj.OBJECT_ID com_util_id,\n" +
            "com_util_name.VALUE com_util_name,\n" +
            "com_util_coeff.VALUE com_util_coeff,\n" +
            "com_util_status_list.VALUE com_util_status,\n" +
            "com_util_dline.date_value com_util_dline,\n" +
            "com_util_calc_list.VALUE com_util_calc,\n" +
            "com_util_durtype_list.Value com_util_durtype\n" +
            "    from\n" +
            "    OBJECTS com_util_obj,\n" +
            "    ATTRIBUTES com_util_name,\n" +
            "    ATTRIBUTES com_util_coeff,\n" +
            "    LISTS com_util_status_list,\n" +
            "    ATTRIBUTES com_util_status,\n" +
            "    ATTRIBUTES com_util_dline,\n" +
            "    LISTS com_util_calc_list,\n" +
            "    ATTRIBUTES com_util_calc,\n" +
            "    LISTS com_util_durtype_list,\n" +
            "    ATTRIBUTES com_util_durtype\n" +
            "    where\n" +
            "    com_util_name.attr_id=21\n" +
            "    and com_util_name.VALUE= ? \n" +
            "    and com_util_obj.OBJECT_TYPE_ID=11\n" +
            "    and com_util_name.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_coeff.attr_id=40\n" +
            "    and com_util_coeff.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.attr_id=23\n" +
            "    and com_util_status.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_status.list_value_id = com_util_status_list.list_value_id\n" +
            "    and com_util_calc.attr_id=20\n" +
            "    and com_util_calc.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_calc.list_value_id = com_util_calc_list.list_value_id\n" +
            "    and com_util_dline.attr_id = 24\n" +
            "    and com_util_dline.object_id=com_util_obj.object_id\n" +
            "    and com_util_durtype.attr_id=22\n" +
            "    and com_util_durtype.object_id = com_util_obj.OBJECT_ID\n" +
            "    and com_util_durtype.list_value_id = com_util_durtype_list.list_value_id\n";

    String createCommunalUtility = "insert all\n" +
            "into OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)\n" +
            "VALUES (OBJ_ID_SEQ.nextval ,null ,11 , null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (20 ,OBJ_ID_SEQ.currval,null ,null,? )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (21 ,OBJ_ID_SEQ.currval,? ,null,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (22 ,OBJ_ID_SEQ.currval,null,null,? )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (23 ,OBJ_ID_SEQ.currval,null,null,? )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (24 ,OBJ_ID_SEQ.currval,null,?,null )\n" +
            "into ATTRIBUTES (attr_id, object_id, value, date_value, list_value_id)\n" +
            "values (40 ,OBJ_ID_SEQ.currval,?,null,null )\n" +
            "select * from dual";

    String updateCommunalUtility = "merge into ATTRIBUTES x\n" +
            "            using (SELECT 21 ATTR_ID, ?    VALUE, null DATE_VALUE, null list_value_id from dual\n" +
            "                   union all\n" +
            "                   SELECT 20 ATTR_ID, null VALUE ,null DATE_VALUE, ?    list_value_id from dual\n" +
            "                   union all\n" +
            "                   select 22 ATTR_ID, null VALUE, null DATE_VALUE, ?    list_value_id from DUAL\n" +
            "                   union all\n" +
            "                   select 23 ATTR_ID, null VALUE, null DATE_VALUE, ?     list_value_id from DUAL\n" +
            "                   union all\n" +
            "                   SELECT 40 ATTR_ID, ?    VALUE ,null DATE_VALUE, null list_value_id from dual\n" +
            "                   union all\n" +
            "                   select 24 ATTR_ID, null VALUE, ?    DATE_VALUE, null list_value_id from DUAL\n" +
            "            ) y\n" +
            "            on (x.OBJECT_ID = ? and x.ATTR_ID = y.ATTR_ID)\n" +
            "            WHEN MATCHED THEN\n" +
            "                UPDATE SET x.VALUE = y.VALUE,\n" +
            "                           x.DATE_VALUE = y.DATE_VALUE,\n" +
            "                           x.list_value_id = y.list_value_id \n" +
            "where x.VALUE <> y.VALUE OR x.DATE_VALUE <> y.DATE_VALUE OR x.LIST_VALUE_ID <> y.LIST_VALUE_ID";

    String EXCEPTION_GET_ALL_COMMUNAL_UTILITIES = "Can't get communal utilities";
    String EXCEPTION_GET_ALL_COMMUNAL_UTILITIES_FILTER_BY_STATUS = "Can't get communal utilities by status: ";
    String EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID = "Can't get communal utility with id:";
    String EXCEPTION_GET_UNIQUE_COMMUNAL_UTILITY = "Can't get unique communal utility";
    String EXCEPTION_UPDATE_COMMUNAL_UTILITY = "Can't update communal utility with id:";
    String EXCEPTION_CREATE_COMMUNAL_UTILITIES = "Can't create communal utility with id:";


    List<CommunalUtility> getAllCommunalUtilities();

    List<CommunalUtility> getAllCommunalUtilitiesFilterByStatus(CommunalUtility.Status status);

    CommunalUtility getCommunalUtilityById(BigInteger id);

    void updateCommunalUtility(CommunalUtility communalUtility);

    void createCommunalUtility(CommunalUtility communalUtility);

    CommunalUtility getUniqueCommunalUtility(CommunalUtility communalUtility);

}
