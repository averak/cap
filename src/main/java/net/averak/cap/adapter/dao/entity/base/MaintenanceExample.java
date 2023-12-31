package net.averak.cap.adapter.dao.entity.base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public MaintenanceExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("`id` like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("`id` not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOpenAtIsNull() {
            addCriterion("`open_at` is null");
            return (Criteria) this;
        }

        public Criteria andOpenAtIsNotNull() {
            addCriterion("`open_at` is not null");
            return (Criteria) this;
        }

        public Criteria andOpenAtEqualTo(LocalDateTime value) {
            addCriterion("`open_at` =", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtNotEqualTo(LocalDateTime value) {
            addCriterion("`open_at` <>", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtGreaterThan(LocalDateTime value) {
            addCriterion("`open_at` >", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`open_at` >=", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtLessThan(LocalDateTime value) {
            addCriterion("`open_at` <", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`open_at` <=", value, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtIn(List<LocalDateTime> values) {
            addCriterion("`open_at` in", values, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtNotIn(List<LocalDateTime> values) {
            addCriterion("`open_at` not in", values, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`open_at` between", value1, value2, "openAt");
            return (Criteria) this;
        }

        public Criteria andOpenAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`open_at` not between", value1, value2, "openAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtIsNull() {
            addCriterion("`close_at` is null");
            return (Criteria) this;
        }

        public Criteria andCloseAtIsNotNull() {
            addCriterion("`close_at` is not null");
            return (Criteria) this;
        }

        public Criteria andCloseAtEqualTo(LocalDateTime value) {
            addCriterion("`close_at` =", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtNotEqualTo(LocalDateTime value) {
            addCriterion("`close_at` <>", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtGreaterThan(LocalDateTime value) {
            addCriterion("`close_at` >", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("`close_at` >=", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtLessThan(LocalDateTime value) {
            addCriterion("`close_at` <", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("`close_at` <=", value, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtIn(List<LocalDateTime> values) {
            addCriterion("`close_at` in", values, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtNotIn(List<LocalDateTime> values) {
            addCriterion("`close_at` not in", values, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`close_at` between", value1, value2, "closeAt");
            return (Criteria) this;
        }

        public Criteria andCloseAtNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("`close_at` not between", value1, value2, "closeAt");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("`memo` is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("`memo` is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("`memo` =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("`memo` <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("`memo` >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("`memo` >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("`memo` <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("`memo` <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("`memo` like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("`memo` not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("`memo` in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("`memo` not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("`memo` between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("`memo` not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table
     * maintenance
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table
     * maintenance
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
