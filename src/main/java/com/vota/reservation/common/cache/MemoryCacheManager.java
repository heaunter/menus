package com.vota.reservation.common.cache;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryCacheManager {

	private static Lock lock = new ReentrantLock();
	private static int maxCapacity = 20;
	private static Map<String, String> eden = new ConcurrentHashMap<String, String>(maxCapacity);
	private static Map<String, String> perm = new WeakHashMap<String, String>(maxCapacity);

	public static String get(String k) {
		String v = eden.get(k);
		if (v == null) {
			lock.lock();
			try {
				v = perm.get(k);
			} finally {
				lock.unlock();
			}
			if (v != null) {
				eden.put(k, v);
			}
		}
		return v;
	}

	public static void put(String key, String value) {
		if (eden.size() >= maxCapacity) {
			lock.lock();
			try {
				perm.putAll(eden);
			} finally {
				lock.unlock();
			}
			eden.clear();
		}
		eden.put(key, value);
	}
}
