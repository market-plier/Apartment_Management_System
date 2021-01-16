package com.netcracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.sql.Date;
@EqualsAndHashCode
@Data
public class CommunalUtility {
    public enum Status {
        Enabled(3),
        Disabled(4);
        private final int statusCode;

        Status(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public enum Duration {
        Temporary(1),
        Constant(2);
        private final int durationCode;

        Duration(int durationCode) {
            this.durationCode = durationCode;
        }

        public int getDurationCode() {
            return this.durationCode;
        }
    }

    @NotNull(message = "durationType can not be null")
    private Duration durationType;
    @Positive
    private BigInteger communalUtilityId;

    @NotBlank(message = "name not specified")
    @NotNull(message = "name not specified")
    private String name;
    @NotNull(message = "status can not be null")
    private Status status;
    @NotNull(message = "deadLine can not be null")
    private Date deadline;
    @NotNull(message = "CalculationMethod can not be null")
    private CalculationMethod calculationMethod;
    @NotNull(message = "coefficient can't be null")
    @Positive(message = "coefficient should be positive")
    private Double coefficient;

    public CommunalUtility(BigInteger communalUtilityId, CalculationMethod calculationMethod,
                           String name, Duration durationType, Status status, Date deadline, Double coefficient) {
        this.communalUtilityId = communalUtilityId;
        this.calculationMethod = calculationMethod;
        this.name = name;
        this.durationType = durationType;
        this.status = status;
        this.deadline = deadline;
        this.coefficient = coefficient;
    }

    public enum CalculationMethod {
        SquareMeters(7),
        PeopleCount(8),
        Floor(9);
        private final int calculationMethodCode;

        CalculationMethod(int calculationMethodCode) {
            this.calculationMethodCode = calculationMethodCode;
        }

        public int getCalculationMethodCode() {
            return this.calculationMethodCode;
        }
    }


}
