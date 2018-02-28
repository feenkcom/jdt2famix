package com.feenk.jdt2famix.injava;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

/**
 * A simple visitor for computing the metric 
 * Cyclomatic Complexity. The implementation is based on 
 * the paper: 
 * 
 * Empirical analysis of the relationship between CC and SLOC in a large corpus of Java methods and C functions
 * Authors: Davy Landman, Alexander Serebrenik, Eric Bouwers, Jurgen J Vinju
 *  
 * @author rbonifacio@unb.br
 */
public class CCVisitor extends ASTVisitor {
	private int complexity; 
	
	public CCVisitor() {
		complexity++; 
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		complexity++;
		return super.visit(node);
	}

	@Override
	public boolean visit(ForStatement node) {
		complexity++;
		return super.visit(node);
	}

	@Override
	public boolean visit(IfStatement node) {
		complexity++;
		return super.visit(node);
	}

	@Override
	public boolean visit(SwitchCase node) {
		complexity++;
		return super.visit(node);
	}

	@Override
	public boolean visit(CatchClause node) {
		complexity ++;
		return super.visit(node);
	}

	@Override
	public boolean visit(WhileStatement node) {
		complexity++; 
		return super.visit(node);
	}
	
	
	@Override
	public boolean visit(ConditionalExpression node) {
		complexity++;
		return super.visit(node);
	}

	@Override
	public boolean visit(InfixExpression node) {
		if(node.getOperator().equals(Operator.AND) || node.getOperator().equals(Operator.OR)) {
			complexity++;
		}
		return super.visit(node);
	}

	public int getComplexity() {
		return complexity; 
	}
}
