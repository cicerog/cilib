/**
 * Copyright (C) 2003 - 2009
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
 */
package net.sourceforge.cilib.pso.dynamic.detectionstrategies;

import java.util.ArrayList;

import net.sourceforge.cilib.algorithm.population.PopulationBasedAlgorithm;
import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.entity.Topology;
import net.sourceforge.cilib.math.random.generator.MersenneTwister;
import net.sourceforge.cilib.math.random.generator.Random;

/**
 * @author Anna Rakitianskaia
 * @deprecated rather use {@link RandomSentriesDetectionStrategy}
 */
public class RandomSentryDetectionStrategy<E extends PopulationBasedAlgorithm> extends EnvironmentChangeDetectionStrategy<E> {
    private static final long serialVersionUID = -7961604921868908664L;
    
    private int sentries;
    private double theta; 
    private Random randomiser;

    public RandomSentryDetectionStrategy() {
        sentries = 1;
        theta = 0.001;
        randomiser = new MersenneTwister();
    }
    
    public RandomSentryDetectionStrategy(RandomSentryDetectionStrategy<E> copy) {
        this.sentries = copy.sentries;
        this.theta = copy.theta;
        this.randomiser = copy.randomiser.getClone();
    }
    
    public RandomSentryDetectionStrategy<E> getClone() {
        return new RandomSentryDetectionStrategy<E>(this);
    }
    

    /** Check for environment change:
     * Pick the specified number of random particles (sentries) and evaluate their current positions. 
     * If the difference between the old fitness and the newly generated one is significant (exceeds a predefined theta)
     * for one or more of the sentry particles, assume that the environment has changed.
     * @param algorithm PSO algorithm that operates in a dynamic environment
     * @return true if any changes are detected, false otherwise
     */        
    public boolean detect(E algorithm) {
        Topology<? extends Entity> topology = algorithm.getTopology();

        boolean envChangeOccured = false;
        ArrayList<Entity> sentryList = new ArrayList<Entity>();
        int populationSize = topology.size();
                
        for (int i = 0; i < sentries; i++) {
            int index = randomiser.nextInt(populationSize);
            sentryList.add((Entity)topology.get(index));
        }
        
        for (Entity nextSentry : sentryList) {
            double oldSentryFitness = nextSentry.getFitness().getValue();
            nextSentry.calculateFitness(false);
            double newSentryFitness = algorithm.getOptimisationProblem().getFitness(nextSentry.getCandidateSolution(), false).getValue();
            
            if(Math.abs(oldSentryFitness - newSentryFitness) >=  theta) {
                envChangeOccured = true;
                break;
            }
        }
        return envChangeOccured;
    }

    /**
     * @return the randomiser
     */
    public Random getRandomiser() {
        return randomiser;
    }

    /**
     * @param randomiser the randomiser to set
     */
    public void setRandomiser(Random randomiser) {
        this.randomiser = randomiser;
    }

    /**
     * @return the sentries
     */
    public int getSentries() {
        return sentries;
    }

    /**
     * @param sentries the sentries to set
     */
    public void setSentries(int sentries) {
        this.sentries = sentries;
    }

    /**
     * @return the theta
     */
    public double getTheta() {
        return theta;
    }

    /**
     * @param theta the theta to set
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }
}
