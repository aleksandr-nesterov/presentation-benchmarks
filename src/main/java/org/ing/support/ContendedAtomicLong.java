package org.ing.support;

import sun.misc.Contended;

import java.util.concurrent.atomic.AtomicLong;

@Contended
public class ContendedAtomicLong extends AtomicLong {
}
