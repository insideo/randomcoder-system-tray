package org.randomcoder.systray.peer;

import java.awt.*;
import java.beans.PropertyChangeListener;

/**
 * System tray peer implementation, used by <code>SystemTray</code> via
 * reflection.
 * 
 * <pre>
 * Copyright (c) 2007, Craig Condit. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot;
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * </pre>
 */
public class SystemTrayPeerImpl implements SystemTrayPeer
{
	private final SystemTray peer;
	
	public SystemTrayPeerImpl(SystemTray peer)
	{
		this.peer = peer;
	}
	
	public static SystemTray getSystemTray()
	{
		return SystemTray.getSystemTray();
	}
	
	public static boolean isSupported()
	{
		return SystemTray.isSupported();
	}

	public Object getNativePeer()
	{
		return peer;
	}

	public void add(TrayIconPeer trayIcon) throws AWTException
	{
		peer.add((TrayIcon) trayIcon.getNativePeer()); 
	}

	public void remove(TrayIconPeer trayIcon)
	{
		peer.remove((TrayIcon) trayIcon.getNativePeer());
	}

	public TrayIconPeer[] getTrayIcons()
	{
		TrayIcon[] icons = peer.getTrayIcons();
		TrayIconPeer[] wrappedIcons = new TrayIconPeer[icons.length];
		
		for (int i = 0; i < icons.length; i++)
			wrappedIcons[i] = new TrayIconPeerImpl(icons[i]);
		
		return wrappedIcons;
	}
	
	public Dimension getTrayIconSize()
	{
		return peer.getTrayIconSize();
	}

	public void addPropertyChangeListener(
			String propertyName, PropertyChangeListener listener)
	{
		peer.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		peer.removePropertyChangeListener(propertyName, listener);
	}

	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName)
	{
		return peer.getPropertyChangeListeners(propertyName);
	}
}
