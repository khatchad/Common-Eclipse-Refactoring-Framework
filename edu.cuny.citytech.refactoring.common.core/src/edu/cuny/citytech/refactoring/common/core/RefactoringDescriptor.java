/**
 *
 */
package edu.cuny.citytech.refactoring.common.core;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;

/**
 * @author <a href="mailto:rkhatchadourian@citytech.cuny.edu">Raffi Khatchadourian</a>
 */
public abstract class RefactoringDescriptor extends org.eclipse.ltk.core.refactoring.RefactoringDescriptor {

	@SuppressWarnings("rawtypes")
	protected final Map fArguments;

	protected RefactoringDescriptor(String refactoringID, String project, String description, String comment,
			@SuppressWarnings("rawtypes") Map arguments) {
		super(refactoringID, project, description, comment, STRUCTURAL_CHANGE | MULTI_CHANGE);

		this.fArguments = arguments;
	}

	protected abstract Refactoring createRefactoring();

	@Override
	public org.eclipse.ltk.core.refactoring.Refactoring createRefactoring(RefactoringStatus status) throws CoreException {
		final Refactoring refactoring = this.createRefactoring();
		status.merge(refactoring.initialize(this.fArguments));
		return refactoring;
	}

	@SuppressWarnings("rawtypes")
	public Map getArguments() {
		return this.fArguments;
	}
}
