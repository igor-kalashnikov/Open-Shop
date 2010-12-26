package by.bsu.fami.openshop.enums;

import by.bsu.fami.openshop.interfaces.Option;
import by.bsu.fami.openshop.resources.ResourcesProvider;

public enum CommonOption implements Option {
	
	MACHINES_COUNT_ANY("openshop.tree.tasksTypes.machinesCount.any"),
	MACHINES_COUNT_1("openshop.tree.tasksTypes.machinesCount.1"),
	MACHINES_COUNT_2("openshop.tree.tasksTypes.machinesCount.2"),
	MACHINES_COUNT_3("openshop.tree.tasksTypes.machinesCount.3"),
	MACHINES_COUNT_M("openshop.tree.tasksTypes.machinesCount.m"),
	
	INTERRUPTS_ALLOWED("openshop.tree.tasksTypes.characteristics.interrupts.allowed"),
	INTERRUPTS_DENIED("openshop.tree.tasksTypes.characteristics.interrupts.denied"),
	
	SERVING_TIME_ANY("openshop.tree.tasksTypes.characteristics.servingTime.any"),
	SERVING_TIME_IDENTITY("openshop.tree.tasksTypes.characteristics.servingTime.1"),
	
	ADDING_TIMES_0("openshop.tree.tasksTypes.characteristics.addingTimes.0"),
	ADDING_TIMES_ANY("openshop.tree.tasksTypes.characteristics.addingTimes.any"),
	
	PRECEDENCE_RELATIONS_NONE("openshop.tree.tasksTypes.characteristics.precedenceRelations.none"),
	PRECEDENCE_RELATIONS_PREC("openshop.tree.tasksTypes.characteristics.precedenceRelations.prec"),
	PRECEDENCE_RELATIONS_TREE("openshop.tree.tasksTypes.characteristics.precedenceRelations.tree"),
	PRECEDENCE_RELATIONS_IN_TREE("openshop.tree.tasksTypes.characteristics.precedenceRelations.in-tree"),
	PRECEDENCE_RELATIONS_OUT_TREE("openshop.tree.tasksTypes.characteristics.precedenceRelations.out-tree"),
	PRECEDENCE_RELATIONS_CHAINS("openshop.tree.tasksTypes.characteristics.precedenceRelations.chains"),
	
	TARGET_FUNCTION_C_MAX("openshop.tree.tasksTypes.targetFunction.C_max"),
	TARGET_FUNCTION_L_MAX("openshop.tree.tasksTypes.targetFunction.L_max"),
	TARGET_FUNCTION_C_J("openshop.tree.tasksTypes.targetFunction.sum(C_j)"),
	TARGET_FUNCTION_U_J("openshop.tree.tasksTypes.targetFunction.sum(U_j)"),
	TARGET_FUNCTION_T_J("openshop.tree.tasksTypes.targetFunction.sum(T_j)"),
	TARGET_FUNCTION_W_J_C_J("openshop.tree.tasksTypes.targetFunction.sum(w_j*C_j)"),
	TARGET_FUNCTION_W_J_U_J("openshop.tree.tasksTypes.targetFunction.sum(w_j*U_j)"),
	TARGET_FUNCTION_W_J_T_J("openshop.tree.tasksTypes.targetFunction.sum(w_j*T_j)");

	@Override
	public String toString() {
		return uiText;
	}
	
	private CommonOption(String key) {
		String fullValue = ResourcesProvider.get().getString(key);
		String[] splitted = fullValue.split("\\|");
		uiText = splitted[0];
		value = splitted[1];
	}
	
	@Override
	public String getOptionValue() {
		return value;
	}
	
	private final String uiText;
	
	private final String value;

}
