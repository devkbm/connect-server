package com.like.hrm.util;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 법용적으로 사용할 수 있는 렉서 클래스
 */
public class Lexer<E> {
	private static class Rule<E> {
        public final Pattern regex;
        public final E kind;

        public Rule(final String rule, final E kind) {
            // this.regex = Pattern.compile("^("+rule+")");
            this.regex = Pattern.compile(rule);
            this.kind = kind;
        }
    }

    final ArrayList<Rule<E>> rules = new ArrayList<Rule<E>>();

    public void add(final String rule, final E kind) {
        rules.add(new Rule<E>(rule, kind));
    }

    public Optional<ArrayList<Token<E>>> lex(String input) {
        ArrayList<Token<E>> result = new ArrayList<Token<E>>();

        while (!input.isEmpty()) {
            boolean failed = true;
            for (Rule<E> rule : rules) {
                Matcher m = rule.regex.matcher(input);
                if (!m.find()) { continue; }

                failed = false;
                result.add(new Token<E>(m.group(), rule.kind));
                input = m.replaceFirst("");
                break;
            }
            // 렉싱에 실패한경우, empty 반환
            if (failed) { return Optional.empty(); }
        }

        // 렉싱 결과물 반환
        return Optional.of(result);
    }
}
