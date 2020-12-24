package com.netcracker.models;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Date;

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

    private BigInteger communalUtilityId;
    @Valid
    private CalculationMethod calculationMethod;
    @NotBlank
    private String name;
    @NotNull
    private Duration durationType;
    @NotNull
    private Status status;
    @NotNull
    private Date deadline;

    public CommunalUtility(BigInteger communalUtilityId, CalculationMethod calculationMethod,
                           String name, Duration durationType, Status status, Date deadline) {
        this.communalUtilityId = communalUtilityId;
        this.calculationMethod = calculationMethod;
        this.name = name;
        this.durationType = durationType;
        this.status = status;
        this.deadline = deadline;
    }
}
