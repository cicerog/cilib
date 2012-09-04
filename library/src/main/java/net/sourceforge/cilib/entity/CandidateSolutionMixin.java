/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.entity;

import java.util.Map;

import net.sourceforge.cilib.problem.solution.Fitness;
import net.sourceforge.cilib.type.types.Blackboard;
import net.sourceforge.cilib.type.types.Type;
import net.sourceforge.cilib.type.types.container.StructuredType;

/**
 * <p>
 * A <code>CandidateSolution</code> is a potential solution within an optimization.
 *
 * <p>
 * <code>CandidateSolution</code> is a base class that all concrete <code>Entity</code>
 * instances inherit from. All <code>Entity</code> objects have their respective contents
 * represented and maintained within the <code>Blackboard</code> as defined by the
 * <code>CandidateSolution</code>.
 */
public class CandidateSolutionMixin implements CandidateSolution {
    private static final long serialVersionUID = 4539668687773346284L;
    private final Blackboard<Enum<?>, Type> properties;

    /**
     * Create the Mixin class, providing the reference to the shared {@linkplain Blackboard}
     * data structure.
     */
    public CandidateSolutionMixin() {
        this.properties = new Blackboard<Enum<?>, Type>();
    }

    /**
     * Instantiate a new instance, based on the provided <code>CandidateSolutionMixin</code>.
     * This is a shallow copy instantiation.
     * @param copy The template object to copy.
     */
    public CandidateSolutionMixin(CandidateSolutionMixin copy) {
        this.properties = new Blackboard<Enum<?>, Type>();

        for (Map.Entry<Enum<?>, Type> entry : copy.properties.entrySet()) {
            this.properties.put(entry.getKey(), entry.getValue().getClone());
        }
    }

    /**
     * Get a clone of the current {@linkplain CandidateSolutionMixin}.
     * @return A shallow clone of the current object - The {@linkplain Blackboard} is not cloned.
     */
    @Override
    public CandidateSolutionMixin getClone() {
        return new CandidateSolutionMixin(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if ((obj == null) || (this.getClass() != obj.getClass()))
            return false;

        CandidateSolutionMixin other = (CandidateSolutionMixin) obj;
        return this.properties.equals(other.properties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.properties == null ? 0 : this.properties.hashCode());
        return hash;
    }

    /**
     * Get the associated candidate solution representation that the current {@linkplain Entity}
     * represents.
     * @return A {@linkplain Type} representing the solution of the {@linkplain Entity}
     */
    @Override
    public StructuredType getCandidateSolution() {
        return (StructuredType) properties.get(EntityType.CANDIDATE_SOLUTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCandidateSolution(StructuredType contents) {
        properties.put(EntityType.CANDIDATE_SOLUTION, contents);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fitness getFitness() {
        return (Fitness) properties.get(EntityType.FITNESS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blackboard<Enum<?>, Type> getProperties() {
        return properties;
    }

}
