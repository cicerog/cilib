/*
 * SortedListTest.java
 * 
 * Created on Nov 15, 2005
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
package net.sourceforge.cilib.container;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import net.sourceforge.cilib.entity.Entity;
import net.sourceforge.cilib.type.types.Int;

import org.junit.Test;

public class SortedListTest {

	public SortedListTest(){
	}
	
	@Test
	public void testSortedAddition() {
		SortedList<Int> intList = new SortedList<Int>();
		
		intList.add(new Int(5));
		intList.add(new Int(8));
		intList.add(new Int(2));
		
		assertEquals(2, intList.get(0).getInt());
		assertEquals(5, intList.get(1).getInt());
		assertEquals(8, intList.get(2).getInt());
	}
	
	
	@Test
	public void testSortedPairAddition() {
		SortedList<Pair<Integer, Entity>> pairList = new SortedList<Pair<Integer, Entity>>(new PairComparator());
		
		Pair<Integer, Entity> p1 = new Pair<Integer, Entity>(3, null);
		Pair<Integer, Entity> p3 = new Pair<Integer, Entity>(99, null);
		Pair<Integer, Entity> p4 = new Pair<Integer, Entity>(-9, null);
		Pair<Integer, Entity> p2 = new Pair<Integer, Entity>(0, null);
		Pair<Integer, Entity> p5 = new Pair<Integer, Entity>(0, null);
		
		pairList.add(p1);
		pairList.add(p2);
		pairList.add(p3);
		pairList.add(p4);
		pairList.add(p5);
		
		assertEquals(-9, (int) pairList.get(0).getKey());
		assertEquals(0,  (int) pairList.get(1).getKey());
		assertEquals(0,  (int) pairList.get(2).getKey());
		assertEquals(3,  (int) pairList.get(3).getKey());
		assertEquals(99, (int) pairList.get(4).getKey());
	}
	
	
	private class PairComparator implements Comparator<Pair<Integer, Entity>> {

		public int compare(Pair<Integer, Entity> o1, Pair<Integer, Entity> o2) {
			return o1.getKey().compareTo(o2.getKey());
		}
		
	}
	
}
