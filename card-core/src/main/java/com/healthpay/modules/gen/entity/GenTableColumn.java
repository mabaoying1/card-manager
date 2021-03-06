package com.healthpay.modules.gen.entity;

import com.google.common.collect.Lists;
import com.healthpay.common.persistence.DataEntity;
import com.healthpay.common.utils.StringUtils;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public class GenTableColumn extends DataEntity<GenTableColumn> {
	private static final long serialVersionUID = 1L;
	private GenTable genTable;
	private String name;
	private String comments;
	private String jdbcType;
	private String javaType;
	private String javaField;
	private String isPk;
	private String isNull;
	private String isInsert;
	private String isEdit;
	private String isForm;
	private String isList;
	private String isQuery;
	private String queryType;
	private String showType;
	private String dictType;
	private Integer sort;
	private String tableName;
	private String fieldLabels;
	private String fieldKeys;
	private String searchLabel;
	private String searchKey;

	public GenTableColumn() {
	}

	public GenTableColumn(String name, String comments, String jdbcType, String javaType, String javaField, String isPk,
			String isNull, String isInsert, String isEdit, String isForm, String isList, String isQuery,
			String queryType, String showType, String dictType) {
		this.name = name;
		this.comments = comments;
		this.jdbcType = jdbcType;
		this.javaType = javaType;
		this.javaField = javaField;
		this.isPk = isPk;
		this.isNull = isNull;
		this.isInsert = isInsert;
		this.isEdit = isEdit;
		this.isForm = isForm;
		this.isList = isList;
		this.isQuery = isQuery;
		this.queryType = queryType;
		this.showType = showType;
		this.dictType = dictType;
	}

	public GenTableColumn(String id) {
		super(id);
	}

	public GenTableColumn(GenTable genTable) {
		this.genTable = genTable;
	}

	public GenTable getGenTable() {
		return this.genTable;
	}

	public void setGenTable(GenTable genTable) {
		this.genTable = genTable;
	}

	@Length(min = 1, max = 200)
	public String getName() {
		return StringUtils.lowerCase(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getJdbcType() {
		return StringUtils.lowerCase(this.jdbcType);
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		return this.javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJavaField() {
		return this.javaField;
	}

	public void setJavaField(String javaField) {
		this.javaField = javaField;
	}

	public String getIsPk() {
		return this.isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getIsNull() {
		return this.isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getIsInsert() {
		return this.isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}

	public String getIsEdit() {
		return this.isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public void setIsForm(String isForm) {
		this.isForm = isForm;
	}

	public String getIsForm() {
		return this.isForm;
	}

	public String getIsList() {
		return this.isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getIsQuery() {
		return this.isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getQueryType() {
		return this.queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getShowType() {
		return this.showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getDictType() {
		if (this.dictType == null)
			return "";
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNameAndComments() {
		return getName() + (this.comments == null ? "" : new StringBuilder("  :  ").append(this.comments).toString());
	}

	public String getDataLength() {
		String[] ss;
		if (((ss = StringUtils.split(StringUtils.substringBetween(getJdbcType(), "(", ")"), ",")) != null)
				&& (ss.length == 1)) {
			return ss[0];
		}
		return "0";
	}

	public String getSimpleJavaType() {
		if ("This".equals(getJavaType())) {
			return StringUtils.capitalize(this.genTable.getClassName());
		}
		if (StringUtils.indexOf(getJavaType(), ".") != -1)
			return StringUtils.substringAfterLast(getJavaType(), ".");
		return getJavaType();
	}

	public String getSimpleJavaField() {
		return StringUtils.substringBefore(getJavaField(), ".");
	}

	public String getJavaFieldId() {
		return StringUtils.substringBefore(getJavaField(), "|");
	}

	public String getJavaFieldName() {
		String[][] ss;
		if ((ss = getJavaFieldAttrs()).length > 0)
			return getSimpleJavaField() + "." + ss[0][0];
		return "";
	}

	public String[][] getJavaFieldAttrs() {
		String[] ss;
		String[][] sss = new String[(ss = StringUtils.split(StringUtils.substringAfter(getJavaField(), "|"),
				"|")).length][2];
		if (ss != null) {
			for (int i = 0; i < ss.length; i++) {
				sss[i][0] = ss[i];
				sss[i][1] = StringUtils.toUnderScoreCase(ss[i]);
			}
		}
		return sss;
	}

	public List<String> getAnnotationList() {
		List<String> list = Lists.newArrayList();

		if ("This".equals(getJavaType())) {
			list.add("com.fasterxml.jackson.annotation.JsonBackReference");
		}
		if ("java.util.Date".equals(getJavaType())) {
			list.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
		}

		if ((!"1".equals(getIsNull())) && (!"String".equals(getJavaType()))) {
			list.add("javax.validation.constraints.NotNull(message=\"" + getComments() + "不能为空\")");
		} else if ((!"1".equals(getIsNull())) && ("String".equals(getJavaType())) && (!"0".equals(getDataLength()))) {
			list.add("org.hibernate.validator.constraints.Length(min=1, max=" + getDataLength() + ", message=\""
					+ getComments() + "长度必须介于 1 和 " + getDataLength() + " 之间\")");
		} else if (("String".equals(getJavaType())) && (!"0".equals(getDataLength()))) {
			list.add("org.hibernate.validator.constraints.Length(min=0, max=" + getDataLength() + ", message=\""
					+ getComments() + "长度必须介于 0 和 " + getDataLength() + " 之间\")");
		}
		return list;
	}

	public List<String> getSimpleAnnotationList() {
		List<String> list = Lists.newArrayList();
		for (String ann : getAnnotationList()) {
			list.add(StringUtils.substringAfterLast(ann, "."));
		}
		return list;
	}

	public Boolean getIsNotBaseField() {
		if ((!StringUtils.equals(getSimpleJavaField(), "id")) && (!StringUtils.equals(getSimpleJavaField(), "remarks"))
				&& (!StringUtils.equals(getSimpleJavaField(), "createBy"))
				&& (!StringUtils.equals(getSimpleJavaField(), "createDate"))
				&& (!StringUtils.equals(getSimpleJavaField(), "updateBy"))
				&& (!StringUtils.equals(getSimpleJavaField(), "updateDate"))
				&& (!StringUtils.equals(getSimpleJavaField(), "delFlag")))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setFieldLabels(String fieldLabels) {
		this.fieldLabels = fieldLabels;
	}

	public String getFieldLabels() {
		return this.fieldLabels;
	}

	public void setFieldKeys(String fieldKeys) {
		this.fieldKeys = fieldKeys;
	}

	public String getFieldKeys() {
		return this.fieldKeys;
	}

	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}

	public String getSearchLabel() {
		return this.searchLabel;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchKey() {
		return this.searchKey;
	}
}