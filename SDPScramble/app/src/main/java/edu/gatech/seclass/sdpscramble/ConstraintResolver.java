package edu.gatech.seclass.sdpscramble;

import java.util.HashMap;
import java.util.Map;

/**
 * This class helps in resolving multiple constraints. One can add multiple constraints,
 * and register a call back to get notified when all the constraints are met. After adding the constraint
 * the implementer shall notify when a constraint is met.
 * <p/>
 * This can be used to synchronise multiple events such as showSuccess login button if and only if all the details
 * are filled.
 * <p/>
 * Created by viveksoneja.
 */
public class ConstraintResolver {
    private HashMap<String, Boolean> constraintMap;
    private ConstraintResolverCallback constraintResolverCallback;
    private boolean isAllConstraintsAreMet;

    public ConstraintResolver() {
        constraintMap = new HashMap<>();
        setAllConstraintsAreMet(true);
    }


    public void addConstraintResolverCallback(ConstraintResolverCallback constraintResolverCallback) {
        this.constraintResolverCallback = constraintResolverCallback;
    }

    public void addConstraint(String constraintName) {
        constraintMap.put(constraintName, false);
        validateAndProvideCallback();
    }

    private void validateAndProvideCallback() {
        boolean isSatisfied = true;
        for (Map.Entry<String, Boolean> entry : constraintMap.entrySet()) {
            isSatisfied = isSatisfied & entry.getValue();

            if (isAllConstraintsAreMet && !entry.getValue()) {
                setAllConstraintsAreMet(false);
                break;
            }
        }

        if (isSatisfied && !isAllConstraintsAreMet) {
            setAllConstraintsAreMet(true);
        }
    }

    private void setAllConstraintsAreMet(boolean allConstraintsAreMet) {
        isAllConstraintsAreMet = allConstraintsAreMet;
        if (constraintResolverCallback != null) {
            if (isAllConstraintsAreMet) constraintResolverCallback.onConstraintsAreMet();
            else constraintResolverCallback.onConstraintsAreNotMet();
        }
    }

    public void changeConstraint(String constraintName, boolean isConstraintMet) {
        if (isConstraintMet) {
            constraintMap.put(constraintName, true);
        } else {
            constraintMap.put(constraintName, false);
        }

        validateAndProvideCallback();
    }

    public boolean isAllConstraintsMet() {
        return isAllConstraintsAreMet;
    }

    /**
     * @return Number of unresolved constraints
     */
    public int getNumberOfUnresolvedConstraints() {
        return constraintMap.size();
    }

    public HashMap<String, Boolean> getConstrains() {
        return constraintMap;
    }

    public boolean isAllConstraintsAreMet() {
        return isAllConstraintsAreMet;
    }

    public void setAllConstraintsMet(boolean isAllConstraintsAreMet) {
        this.isAllConstraintsAreMet = isAllConstraintsAreMet;
    }

    public boolean hasConstraint(String constraint) {
        return constraintMap.containsKey(constraint);
    }

    /**
     * Remove all the constraints
     */
    public void reset() {
        constraintMap.clear();
    }

    interface ConstraintResolverCallback {
        /**
         * Called when all the constraints are met
         */
        void onConstraintsAreMet();

        /**
         * Called when constraints were satisfied, and due to event its again in unsatisfied state
         */
        void onConstraintsAreNotMet();
    }
}
