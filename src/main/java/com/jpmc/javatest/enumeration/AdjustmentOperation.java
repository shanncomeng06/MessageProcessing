package com.jpmc.javatest.enumeration;

public enum AdjustmentOperation {
	ADD("add"),
	SUBTRACT("subtract"),
	MULTIPLY("multiply");
	
	private String operationName;

	AdjustmentOperation(String operationName) {
        this.operationName = operationName;
    }

    public String operationName() {
        return operationName;
    }
   
    public static AdjustmentOperation fromString(String parameterName) {
        if (parameterName != null) {
            for (AdjustmentOperation objType : AdjustmentOperation.values()) {
                if (parameterName.equalsIgnoreCase(objType.operationName)) {
                    return objType;
                }
            }
        }
        return null;
    }
}
