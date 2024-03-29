package br.com.furb.copilador.tela;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class LimitLinesDocumentListener implements DocumentListener
{
	private int maximumLines;
	private boolean isRemoveFromStart;

	public LimitLinesDocumentListener(int maximumLines)
	{
		this(maximumLines, false);
	}

	public LimitLinesDocumentListener(int maximumLines, boolean isRemoveFromStart)
	{
		setLimitLines(maximumLines);
		this.isRemoveFromStart = isRemoveFromStart;
	}


	public void setLimitLines(int maximumLines)
	{
		if (maximumLines < 1)
		{
			String message = "Maximum lines must be greater than 0";
			throw new IllegalArgumentException(message);
		}

		this.maximumLines = maximumLines;
	}

	public void insertUpdate(final DocumentEvent e)
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void run()
			{
				removeLines(e);
			}
		});
	}

	public void removeUpdate(DocumentEvent e) {}
	public void changedUpdate(DocumentEvent e) {}

	private void removeLines(DocumentEvent e)
	{
		Document document = e.getDocument();
		Element root = document.getDefaultRootElement();

		while (root.getElementCount() > maximumLines)
		{
			if (isRemoveFromStart)
			{
				removeFromStart(document, root);
			}
			else
			{
				removeFromEnd(document, root);
			}
		}
	}

	private void removeFromStart(Document document, Element root)
	{
		Element line = root.getElement(0);
		int end = line.getEndOffset();

		try
		{
			document.remove(0, end);
		}
		catch(BadLocationException ble)
		{
			System.out.println(ble);
		}
	}

	private void removeFromEnd(Document document, Element root)
	{
		Element line = root.getElement(root.getElementCount() - 1);
		int start = line.getStartOffset();
		int end = line.getEndOffset();

		try
		{
			document.remove(start - 1, end - start);
		}
		catch(BadLocationException ble)
		{
			System.out.println(ble);
		}
	}
}