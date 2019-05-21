package com.windea.utility.generator;

import com.windea.utility.annotation.InfiniteLoopPossible;
import com.windea.utility.annotation.NotImplemented;
import com.windea.utility.exception.NotImplementedException;
import com.windea.utility.utils.MathUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringGen implements TextGen<StringGen> {
	public static String cr = "\r";
	public static String lf = "\n";
	public static String tab = "\t";
	public static String lSep = "\\";
	public static String rSep = "/";


	private static final Log log = LogFactory.getLog(StringGen.class);

	private StringBuilder builder = new StringBuilder();


	private StringGen() {
	}

	public static StringGen empty() {
		return new StringGen();
	}

	public static StringGen fromFile(Path path) throws Exception {
		var text = Files.readString(path);
		return from(text);
	}

	public static StringGen from(String... texts) {
		return new StringGen().add(texts);
	}

	public static StringGen from(StringGen... generators) {
		var texts = Arrays.stream(generators).map(StringGen::text).toArray(String[]::new);
		return from(texts);
	}

	public static StringGen from(Stream<String> stream) {
		var texts = stream.toArray(String[]::new);
		return from(texts);
	}

	public static StringGen join(@NotNull String joinText, @NotNull String... texts) {
		var text = String.join(joinText, texts);
		return from(text);
	}

	public static StringGen join(@NotNull String joinText, @NotNull StringGen... generators) {
		var texts = Arrays.stream(generators).map(StringGen::text).toArray(String[]::new);
		return join(joinText, texts);
	}

	public static StringGen join(@NotNull String joinText, @NotNull Stream<String> stream) {
		var texts = stream.toArray(String[]::new);
		return join(joinText, texts);
	}


	//添加方法

	public StringGen add(@NotNull String text) {
		builder.append(text);
		return this;
	}

	public StringGen add(@NotNull String... texts) {
		for(var text : texts) {
			builder.append(text);
		}
		return this;
	}

	public StringGen surround(@Nullable String fix, boolean ignoreEmpty) {
		return surround(fix, fix, ignoreEmpty);
	}

	public StringGen surround(@Nullable String prefix, @Nullable String suffix, boolean ignoreEmpty) {
		var doSurround = !ignoreEmpty || !text().isEmpty();
		return StringGen.from(
			StringGen.from(prefix).where(doSurround).text(),
			StringGen.from(text()).text(),
			StringGen.from(suffix).where(doSurround).text()
		);
	}

	//控制方法

	/**
	 * 如果满足条件，则保留这段文本。
	 */
	public StringGen where(boolean condition) {
		if(condition) {
			return this;
		}
		return StringGen.empty();
	}

	/**
	 * 如果满足条件，则保留这段文本。
	 */
	public <T> StringGen where(@NotNull T pointer, @NotNull Predicate<T> predicate) {
		var condition = predicate.test(pointer);
		return this.where(condition);
	}

	/**
	 * 根据指定的次数，重复这段文本。
	 */
	public <T> StringGen repeat(int count) {
		if(count > 0) {
			var text = text().repeat(count - 1);
			return this.add(text);
		}
		return StringGen.empty();
	}

	/**
	 * 根据指定的条件，重复这段文本。
	 */
	@InfiniteLoopPossible
	public <T> StringGen repeat(@NotNull T pointer, @NotNull Predicate<T> predicate, @NotNull Function<T, T> reducer) {
		var condition = predicate.test(pointer);
		if((condition)) {
			pointer = reducer.apply(pointer);
			condition = predicate.test(pointer);
			while(condition) {
				this.add(text());
				pointer = reducer.apply(pointer);
				condition = predicate.test(pointer);
			}
			return this;
		}
		return StringGen.empty();
	}

	//修改方法

	/**
	 * 插入一段文本。
	 */
	public StringGen insert(int offset, @NotNull String text) {
		builder.insert(offset, text);
		return this;
	}

	/** 删除一段文本。 */
	public StringGen delete(int start) {
		return delete(start, length());
	}

	/**
	 * 删除一段文本。如果结束位置为负数，则从文本末尾开始计数。
	 */
	public StringGen delete(int start, int end) {
		start = Math.max(0, start);
		end = end < 0 ? text().length() - end : end;
		end = MathUtils.clamp(end, start, text().length());
		builder.delete(start, end);
		return this;
	}

	/**
	 * 替换一段文本。
	 */
	public StringGen replace(int start, int end, @NotNull String text) {
		start = Math.max(0, start);
		end = end < 0 ? text().length() - end : end;
		end = MathUtils.clamp(end, start, text().length());
		builder.replace(start, end, text);
		return this;
	}

	/**
	 * 替换文本。
	 */
	public StringGen replace(@NotNull String target, @NotNull String replacement) {
		var text = text().replace(target, replacement);
		return StringGen.from(text);
	}

	/**
	 * 使用正则表达式替换文本。
	 */
	public StringGen reReplace(@NotNull String pattern, @NotNull String replacement) {
		var text = text().replaceAll(pattern, replacement);
		return StringGen.from(text);
	}

	//格式化方法

	/**
	 * 格式化文本。使用索引占位符{@code "{0}"}。<br>
	 * 用法示例：{@code gen.add("hello {0}").format("world";)}。
	 * @see MessageFormat
	 */
	public StringGen formatByNum(Object... args) {
		try {
			var text = MessageFormat.format(text(), args);
			return StringGen.from(text);
		} catch(IllegalArgumentException e) {
			log.warn("Format failed. Please check your args.");
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 格式化文本。使用名字占位符{@code "${name}"}。<br>
	 * 用法示例：{@code var name="world"; gen.add("hello ${name}").format(world)}。
	 * @see MessageFormat
	 */
	@NotImplemented
	public StringGen formatByName(Object... args) {
		throw new NotImplementedException();
	}


	//输出

	@Override
	public String text() {
		return builder.toString();
	}

	@Deprecated
	@Override
	public String toString() {
		return text();
	}
}
