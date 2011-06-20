/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Andreas Sewe - initial implementation
 ******************************************************************************/
package de.bodden.tamiflex.playout.transformation;

import static org.objectweb.asm.Opcodes.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

public class FieldGetTransformation extends Transformation {
	
	private static List<String> fieldGets = Arrays.asList(
			"get",
			"getBoolean",
			"getByte",
			"getChar",
			"getInt",
			"getLong",
			"getFloat",
			"getDouble",
			"getShort");
	
	public FieldGetTransformation() {
		super(Field.class);
	}
	
	@Override
	protected MethodVisitor getTransformationVisitor(String name, String desc, MethodVisitor parent) {
		if (fieldGets.contains(name))
			return new MethodAdapter(parent) {
			
				@Override
				public void visitInsn(int opcode) {
					if (opcode == ARETURN || opcode == IRETURN || opcode == LRETURN || opcode == FRETURN || opcode == DRETURN) {
						mv.visitVarInsn(ALOAD, 0); // Load Field instance
						mv.visitMethodInsn(INVOKESTATIC, "de/bodden/tamiflex/playout/rt/ReflLogger", "fieldGet", "(Ljava/lang/reflect/Field;)V");
					}
					super.visitInsn(opcode);
				}
			};
		else
			return parent;
	}
}
