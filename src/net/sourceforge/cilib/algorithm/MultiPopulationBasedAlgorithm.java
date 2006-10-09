/*
 * MultiPopulationBasedAlgorithm.java
 * 
 * Created on Feb 10, 2006
 *
 * Copyright (C) 2003 - 2006 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package net.sourceforge.cilib.algorithm;

import java.util.List;

import net.sourceforge.cilib.problem.OptimisationProblem;

/**
 * 
 * @author Gary Pampara
 *
 */
public abstract class MultiPopulationBasedAlgorithm extends Algorithm implements OptimisationAlgorithm {
	
	protected List<Algorithm> populations;
	protected OptimisationProblem optimisationProblem;
	

	/**
	 * 
	 */
	public abstract void performIteration();


	public List<Algorithm> getPopulations() {
		return populations;
	}


	public void setPopulations(List<Algorithm> populations) {
		this.populations = populations;
	}
	
	public OptimisationProblem getOptimisationProblem() {
		return this.optimisationProblem;
	}
	
	public void setOptimisationProblem(OptimisationProblem problem) {
		this.optimisationProblem = problem;
	}

}
