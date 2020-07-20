SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `synopsis` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  `view_count` int(11) NULL DEFAULT 0,
  `comment_count` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '俩数之和算法题解C/C++/JAVA/Python', '题目：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。\n你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。\n示例：给定 nums = [2, 7, 11, 15], target = 9，因为 nums[0] + nums[1] = 2 + 7 = 9，所以返回 [0, 1]。\n第一种方法：暴力搜索，时间复杂度高。第二种方法：哈希表，时间复杂度低，但空间复杂度高。', '<p>题目：</p>\n<p>给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。</p>\n<p>你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。</p>\n<p>示例：</p>\n<p>给定 nums = [2, 7, 11, 15], target = 9</p>\n<p>因为 nums[0] + nums[1] = 2 + 7 = 9</p>\n<p>所以返回 [0, 1]</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/two-sum</p>\n<p>第一种方法：暴力搜索，时间复杂度高</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>int *twoSum(int *nums, int numsSize, int target, int *returnSize)\n{\n    for (int i = 0; i &lt; numsSize; i++)\n    {\n        for (int j = i + 1; j &lt; numsSize; j++)\n        {\n            if (nums[i] + nums[j] == target)\n            {\n                int *result = (int *)malloc(2 * sizeof(int));\n                *returnSize = 2;\n                result[0] = i;\n                result[1] = j;\n                return result;\n            }\n        }\n    }\n    return NULL;\n}</code></pre>\n<p>C++解法</p>\n<pre class=\"language-cpp\"><code>class Solution\n{\npublic:\n    vector&lt;int&gt; twoSum(vector&lt;int&gt; &amp;nums, int target)\n    {\n        unordered_map&lt;int, int&gt; map;\n        for (size_t i = 0; i &lt; nums.size(); i++)\n        {\n            int complement = target - nums[i];\n            if (map.find(complement) != map.end())\n            {\n                int result[] = {map[complement], i};\n                return vector&lt;int&gt;(result, result + 2);\n            }\n            map[nums[i]] = i;\n        }\n        return vector&lt;int&gt;{};\n    }\n};</code></pre>\n<p>第二种方法：哈希表，时间复杂度低，但空间复杂度高</p>\n<p>JAVA解法</p>\n<pre class=\"language-java\"><code>class Solution {\n    public int[] twoSum(int[] nums, int target) {\n        Map&lt;Integer, Integer&gt; map = new HashMap&lt;&gt;();\n        for (int i = 0; i &lt; nums.length; i++) {\n            int complement = target - nums[i];\n            if (map.containsKey(complement)) {\n                return new int[] { map.get(complement), i };\n            }\n            map.put(nums[i], i);\n        }\n        return new int[] {};\n    }\n}</code></pre>\n<p>Python3解法</p>\n<pre class=\"language-python\"><code>class Solution:\n    def twoSum(self, nums: List[int], target: int) -&gt; List[int]:\n        hashmap = {}\n        for index, value in enumerate(nums):\n            complement = target - value\n            if complement in hashmap:\n                return {hashmap[complement],index}\n            hashmap[value] = index</code></pre>', 32, '2019-12-22 13:36:49', 78, 1);
INSERT INTO `article` VALUES (2, '俩数相加算法题解C/C++/JAVA/Python', '题目：给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。\n\n如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字 0 之外，这两个数都不会以 0 开头。\n\n示例：输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)  输出：7 -> 0 -> 8  原因：342 + 465 = 807', '<p>题目：</p>\n<p>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。</p>\n<p>如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。</p>\n<p>您可以假设除了数字 0 之外，这两个数都不会以 0 开头。</p>\n<p>示例：</p>\n<p>输入：(2 -&gt; 4 -&gt; 3) + (5 -&gt; 6 -&gt; 4)</p>\n<p>输出：7 -&gt; 0 -&gt; 8</p>\n<p>原因：342 + 465 = 807</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/add-two-numbers</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>struct ListNode *addTwoNumbers(struct ListNode *l1, struct ListNode *l2)\n{\n    struct ListNode *head = (struct ListNode *)malloc(sizeof(struct ListNode));\n    head-&gt;val = 0;\n    head-&gt;next = NULL;\n    struct ListNode *curr = head;\n    int sum = 0, carry = 0;\n    while (l1 != NULL || l2 != NULL)\n    {\n        int x = (l1 == NULL) ? 0 : l1-&gt;val;\n        int y = (l2 == NULL) ? 0 : l2-&gt;val;\n        sum = carry + x + y;\n        carry = sum / 10;\n        curr-&gt;next = (struct ListNode *)malloc(sizeof(struct ListNode));\n        curr = curr-&gt;next;\n        curr-&gt;val = sum % 10;\n        curr-&gt;next = NULL;\n        if (l1 != NULL)\n            l1 = l1-&gt;next;\n        if (l2 != NULL)\n            l2 = l2-&gt;next;\n    }\n    if (carry == 1)\n    {\n        curr-&gt;next = (struct ListNode *)malloc(sizeof(struct ListNode));\n        curr = curr-&gt;next;\n        curr-&gt;val = carry;\n        curr-&gt;next = NULL;\n    }\n    return head-&gt;next;\n}</code></pre>\n<p>C++解法：</p>\n<pre class=\"language-cpp\"><code>class Solution\n{\npublic:\n    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2)\n    {\n        ListNode *head = new ListNode(0);\n        ListNode *curr = head;\n        int sum = 0, carry = 0;\n        while (l1 != NULL || l2 != NULL)\n        {\n            int x = (l1 == NULL) ? 0 : l1-&gt;val;\n            int y = (l2 == NULL) ? 0 : l2-&gt;val;\n            sum = carry + x + y;\n            carry = sum / 10;\n            curr-&gt;next = new ListNode(sum % 10);\n            curr = curr-&gt;next;\n            if (l1 != NULL)\n                l1 = l1-&gt;next;\n            if (l2 != NULL)\n                l2 = l2-&gt;next;\n        }\n        if (carry == 1)\n        {\n            curr-&gt;next = new ListNode(carry);\n            curr = curr-&gt;next;\n        }\n        return head-&gt;next;\n    }\n};</code></pre>\n<p>JAVA解法：</p>\n<pre class=\"language-java\"><code>class Solution {\n    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {\n        ListNode head = new ListNode(0);\n        ListNode curr = head;\n        int carry = 0;\n        while (l1 != null || l2 != null) {\n            int x = (l1 == null) ? 0 : l1.val;\n            int y = (l2 == null) ? 0 : l2.val;\n            int sum = carry + x + y;\n            carry = sum / 10;\n            curr.next = new ListNode(sum % 10);\n            curr = curr.next;\n            if (l1 != null)\n                l1 = l1.next;\n            if (l2 != null)\n                l2 = l2.next;\n        }\n        if (carry &gt; 0) {\n            curr.next = new ListNode(carry);\n        }\n        return head.next;\n    }\n}</code></pre>\n<p>Python3解法：</p>\n<pre class=\"language-python\"><code>class Solution:\n    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -&gt; ListNode:\n        head = ListNode(0)\n        curr = head\n        carry = 0\n        while l1 or l2 :\n            x = l1.val if l1 else 0\n            y = l2.val if l2 else 0\n            sum = carry + x + y\n            carry = sum // 10\n            curr.next = ListNode(sum % 10)\n            curr = curr.next\n            if l1 :\n                l1 = l1.next\n            if l2 :\n                l2 = l2.next\n        if carry == 1:\n            curr.next = ListNode(carry)\n        return head.next</code></pre>', 32, '2019-12-22 14:18:40', 75, 0);
INSERT INTO `article` VALUES (3, '无重复字符的最长子串算法题解C/C++/JAVA/Python', '题目：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。\n示例 1：输入: \"abcabcbb\"，输出: 3   解释: 因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。\n示例 2：输入: \"bbbbb\"，输出: 1  解释: 因为无重复字符的最长子串是 \"b\"，所以其长度为 1。\n示例 3：输入: \"pwwkew\"，输出: 3  解释: 因为无重复字符的最长子串是 \"wke\"，所以其长度为 3。\n请注意，你的答案必须是 子串 的长度，\"pwke\" 是一个子序列，不是子串。', '<p>题目：</p>\n<p>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。</p>\n<p>示例 1：</p>\n<p>输入：\"abcabcbb\"，输出：3&nbsp;</p>\n<p>解释：因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。</p>\n<p>示例 2：</p>\n<p>输入：\"bbbbb\"，输出: 1</p>\n<p>解释：因为无重复字符的最长子串是 \"b\"，所以其长度为 1。</p>\n<p>示例 3：</p>\n<p>输入：\"pwwkew\"，输出: 3</p>\n<p>解释：因为无重复字符的最长子串是 \"wke\"，所以其长度为 3。</p>\n<p>请注意，你的答案必须是 子串 的长度，\"pwke\" 是一个子序列，不是子串。</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>int max(int a, int b)\n{\n    return a &gt; b ? a : b;\n}\nint lengthOfLongestSubstring(char *s)\n{\n    int n = strlen(s);\n    int l[256] = {0};\n    int r = 0;\n    for (int i = 0, j = 0; j &lt; n; j++)\n    {\n        i = max(l[s[j]], i);\n        r = max(j - i + 1, r);\n        l[s[j]] = j + 1;\n    }\n    return r;\n}</code></pre>\n<p>C++解法：</p>\n<pre class=\"language-cpp\"><code>class Solution\n{\npublic:\n    int lengthOfLongestSubstring(string s)\n    {\n        int n = s.length(), len = 0;\n        unordered_map&lt;int, int&gt; map;\n        for (int i = 0, j = 0; j &lt; n; j++)\n        {\n            if (map.find(s[j]) != map.end())\n            {\n                i = max(map[s[j]], i);\n            }\n            len = max(len, j - i + 1);\n            map[s[j]] = j + 1;\n        }\n        return len;\n    }\n};</code></pre>\n<p>JAVA解法：</p>\n<pre class=\"language-java\"><code>class Solution {\n    public int lengthOfLongestSubstring(String s) {\n        int n = s.length(), len = 0;\n        Map&lt;Character, Integer&gt; map = new HashMap&lt;&gt;();\n        for (int i = 0, j = 0; j &lt; n; j++) {\n            if (map.containsKey(s.charAt(j))) {\n                i = Math.max(map.get(s.charAt(j)) + 1, i);\n            }\n            len = Math.max(len, j - i + 1);\n            map.put(s.charAt(j), j);\n        }\n        return len;\n    }\n}</code></pre>\n<p>Python3解法：</p>\n<pre class=\"language-python\"><code>class Solution:\n    def lengthOfLongestSubstring(self, s: str) -&gt; int:\n        n, i = len(s), 0\n        result = 0\n        map = {}\n        for j in range(n):\n            if s[j] in map:\n                i = max(map[s[j]] , i)\n            result = max(result, j - i + 1)\n            map[s[j]] = j + 1\n        return result</code></pre>', 32, '2019-12-22 14:23:32', 69, 0);
INSERT INTO `article` VALUES (4, '最长回文子串算法题解C/C++/JAVA/Python', '题目：给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。\n示例 1：输入：\"babad\"，输出：\"bab\"，注意：\"aba\" 也是一个有效答案。\n示例 2：输入: \"cbbd\"，输出: \"bb\"', '<p>题目：</p>\n<p>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。</p>\n<p>示例 1：</p>\n<p>输入：\"babad\"</p>\n<p>输出：\"bab\"</p>\n<p>注意：\"aba\" 也是一个有效答案。</p>\n<p>示例 2：</p>\n<p>输入: \"cbbd\"</p>\n<p>输出: \"bb\"</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/longest-palindromic-substring</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>\nint max(int a, int b)\n{\n    return a &gt; b ? a : b;\n}\nchar *substr(char *s, int begin, int end)\n{\n    char temp[end - begin + 1];\n    char *string = NULL;\n    if (strlen(s) &lt; 2 || begin &lt; 0 || begin &gt;= end)\n    {\n        return s;\n    }\n    for (int i = begin; i &lt; end; i++)\n    {\n        temp[i - begin] = s[i];\n    }\n    temp[end - begin] = \'\\0\';\n    string = temp;\n    return string;\n}\nint expandCenter(char *s, int left, int right)\n{\n    int n = strlen(s);\n    int l = left, r = right;\n    while (l &gt;= 0 &amp;&amp; r &lt; n &amp;&amp; s[l] == s[r])\n    {\n        l--;\n        r++;\n    }\n    return r - l - 1;\n}\nchar *longestPalindrome(char *s)\n{\n    int n = strlen(s);\n    if (n == 0)\n    {\n        char *a = \"\";\n        return a;\n    }\n    int start = 0, end = 0;\n    int len1 = 0, len2 = 0, len3 = 0;\n    for (int i = 0; i &lt; n; i++)\n    {\n        len1 = expandCenter(s, i, i);\n        len2 = expandCenter(s, i, i + 1);\n        len3 = max(len1, len2);\n        if (len3 &gt; end - start)\n        {\n            start = i - (len3 - 1) / 2;\n            end = i + len3 / 2;\n        }\n    }\n    return substr(s, start, end + 1);\n}\n</code></pre>\n<p>C++解法：</p>\n<pre class=\"language-cpp\"><code>\nclass Solution\n{\npublic:\n    int expandCenter(string s, int left, int right)\n    {\n        int l = left, r = right;\n        while (l &gt;= 0 &amp;&amp; r &lt; s.length() &amp;&amp; s[l] == s[r])\n        {\n            l--;\n            r++;\n        }\n        return r - l - 1;\n    }\n    string longestPalindrome(string s)\n    {\n        if (s.empty())\n        {\n            return \"\";\n        }\n        int start = 0, end = 0;\n        int len1 = 0, len2 = 0, len3 = 0;\n        for (int i = 0; i &lt; s.length(); i++)\n        {\n            len1 = expandCenter(s, i, i);\n            len2 = expandCenter(s, i, i + 1);\n            len3 = max(len1, len2);\n            if (len3 &gt; end - start)\n            {\n                start = i - (len3 - 1) / 2;\n                end = i + len3 / 2;\n            }\n        }\n        return s.substr(start, end - start + 1);\n    }\n};\n</code></pre>\n<p>JAVA解法：</p>\n<pre class=\"language-java\"><code>\nclass Solution {\n    public String longestPalindrome(String s) {\n        if (s == null || s.length() &lt; 1) return \"\";\n        int start = 0, end = 0;\n        for (int i = 0; i &lt; s.length(); i++) {\n            int len1 = expandCenter(s, i, i);\n            int len2 = expandCenter(s, i, i + 1);\n            int len = Math.max(len1, len2);\n            if (len &gt; end - start) {\n                start = i - (len - 1) / 2;\n                end = i + len / 2;\n            }\n        }\n        return s.substring(start, end+1);\n    }\n\n    private int expandCenter(String s, int left, int right) {\n        int n = s.length();\n        int l = left, r = right;\n        while (l &gt;= 0 &amp;&amp; r &lt; n &amp;&amp; s.charAt(l) == s.charAt(r)) {\n            l--;\n            r++;\n        }\n        return r - l - 1;\n    }\n}\n</code></pre>\n<p>Python3解法：</p>\n<pre class=\"language-python\"><code>\nclass Solution:\n    def longestPalindrome(self, s: str) -&gt; str:\n        start, end, n = 0, 0, len(s)\n        if (not s) or (n &lt; 1):\n            return \"\"\n        for i in range(n):\n            len1 = self.expandCenter(s, i, i)\n            len2 = self.expandCenter(s, i, i + 1)\n            len3 = max(len1, len2)\n            if len3 &gt; (end - start):\n                start = i - (len3 - 1) // 2\n                end = i + len3 // 2\n        return s[start : end + 1]\n\n    def expandCenter(self, s: str, left: int, right: int):\n        l, r, n = left, right, len(s)\n        while l &gt;= 0 and r &lt; len(s) and s[l] == s[r]:\n            l -= 1\n            r += 1\n        return r - l - 1\n</code></pre>\n<div id=\"gtx-trans\" style=\"position: absolute; left: 539px; top: 345.8px;\">\n<div class=\"gtx-trans-icon\">&nbsp;</div>\n</div>', 32, '2019-12-22 14:27:25', 70, 0);
INSERT INTO `article` VALUES (5, '正则表达式匹配算法题解C/C++/JAVA/Python', '题目：给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 \'.\' 和 \'*\' 的正则表达式匹配。\n\'.\' 匹配任意单个字符，\'*\' 匹配零个或多个前面的那一个元素，所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。\n说明：s 可能为空，且只包含从 a-z 的小写字母。p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。\n示例 1：输入：s = \"aa\"，p = \"a\"  输出: false  解释: \"a\" 无法匹配 \"aa\" 整个字符串。', '<p>题目：</p>\n<p>给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 \'.\' 和 \'*\' 的正则表达式匹配。</p>\n<p>\'.\' 匹配任意单个字符，\'*\' 匹配零个或多个前面的那一个元素</p>\n<p>所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。</p>\n<p>说明：</p>\n<p>s 可能为空，且只包含从 a-z 的小写字母。</p>\n<p>p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。</p>\n<p>示例 1：</p>\n<p>输入：s = \"aa\"，p = \"a\"</p>\n<p>输出: false</p>\n<p>解释: \"a\" 无法匹配 \"aa\" 整个字符串。</p>\n<p>示例 2：</p>\n<p>输入：s = \"aa\"，p = \"a*\"</p>\n<p>输出: true</p>\n<p>解释: 因为 \'*\' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 \'a\'。因此，字符串 \"aa\" 可被视为 \'a\' 重复了一次。</p>\n<p>示例 3：</p>\n<p>输入：s = \"ab\"，p = \".*\"</p>\n<p>输出: true</p>\n<p>解释: \".*\" 表示可匹配零个或多个（\'*\'）任意字符（\'.\'）。</p>\n<p>示例 4：</p>\n<p>输入：s = \"aab\"，p = \"c*a*b\"</p>\n<p>输出: true</p>\n<p>解释: 因为 \'*\' 表示零个或多个，这里 \'c\' 为 0 个, \'a\' 被重复一次。因此可以匹配字符串 \"aab\"。</p>\n<p>示例 5:</p>\n<p>输入：s = \"mississippi\"，p = \"mis*is*p*.\"</p>\n<p>输出: false</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/regular-expression-matching</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>bool isMatch(char *s, char *p)\n{\n    bool dp[strlen(s) + 1][strlen(p) + 1];\n    dp[strlen(s)][strlen(p)] = true;\n    for (int i = strlen(s); i &gt;= 0; i--)\n    {\n        for (int j = strlen(p) - 1; j &gt;= 0; j--)\n        {\n            bool match = i &lt; strlen(s) &amp;&amp; s[i] == p[j] || p[j] == \'.\';\n            if (j + 1 &lt; strlen(p) &amp;&amp; p[j + 1] == \'*\')\n            {\n                dp[i][j] = dp[i][j + 2] || (match &amp;&amp; dp[i + 1][j]);\n            }\n            else\n            {\n                dp[i][j] = match &amp;&amp; dp[i + 1][j + 1];\n            }\n        }\n    }\n    return dp[0][0];\n}</code></pre>\n<p>C++解法：</p>\n<pre class=\"language-cpp\"><code>class Solution\n{\npublic:\n    bool isMatch(string s, string p)\n    {\n        bool dp[s.length() + 1][p.length() + 1];\n        dp[s.length()][p.length()] = true;\n        for (int i = s.length(); i &gt;= 0; i--)\n        {\n            for (int j = p.length() - 1; j &gt;= 0; j--)\n            {\n                bool match = (i &lt; s.length() &amp;&amp; (s[i] == p[j] || p[j] == \'.\'));\n                if (j + 1 &lt; p.length() &amp;&amp; p[j + 1] == \'*\')\n                {\n                    dp[i][j] = dp[i][j + 2] || match &amp;&amp; dp[i + 1][j];\n                }\n                else\n                {\n                    dp[i][j] = match &amp;&amp; dp[i + 1][j + 1];\n                }\n            }\n        }\n        return dp[0][0];\n    }\n};</code></pre>\n<p>JAVA解法：</p>\n<pre class=\"language-java\"><code>class Solution {\n    public boolean isMatch(String s, String p) {\n        if (p.isEmpty()) {\n            return s.isEmpty();\n        }\n        boolean first_match = (!s.isEmpty() &amp;&amp; (s.charAt(0) == p.charAt(0) || p.charAt(0) == \'.\'));\n        if (p.length() &gt;= 2 &amp;&amp; p.charAt(1) == \'*\') {\n            return (isMatch(s, p.substring(2)) || (first_match &amp;&amp; isMatch(s.substring(1), p)));\n        } else {\n            return first_match &amp;&amp; isMatch(s.substring(1), p.substring(1));\n        }\n    }\n}\n</code></pre>\n<p>Python3解法：</p>\n<pre class=\"language-python\"><code>class Solution:\n    def isMatch(self, s: str, p: str) -&gt; bool:\n        if not p:\n            return not s\n        first_match = bool(s) and p[0] in {s[0], \".\"}\n        if len(p) &gt;= 2 and p[1] == \"*\":\n            return self.isMatch(s, p[2:]) or (first_match and self.isMatch(s[1:], p))\n        else:\n            return first_match and self.isMatch(s[1:], p[1:])</code></pre>', 32, '2019-12-22 14:34:28', 81, 0);
INSERT INTO `article` VALUES (6, '盛最多水的容器算法题解C/C++/JAVA/Python', '题目：给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。\n说明：你不能倾斜容器，且 n 的值至少为 2。\n示例：输入： [1,8,6,2,5,4,8,3,7]，输出：49', '<p>题目：</p>\n<p>给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。</p>\n<p>说明：你不能倾斜容器，且 n 的值至少为 2。</p>\n<p>示例：</p>\n<p>输入： [1,8,6,2,5,4,8,3,7]</p>\n<p>输出：49</p>\n<p>来源：力扣（LeetCode）</p>\n<p>链接：https://leetcode-cn.com/problems/container-with-most-water</p>\n<p>C解法：</p>\n<pre class=\"language-c\"><code>int max(int a, int b)\n{\n    return a &gt; b ? a : b;\n}\nint min(int a, int b)\n{\n    return a &lt; b ? a : b;\n}\nint maxArea(int *height, int heightSize)\n{\n    int area = 0, l = 0, r = heightSize - 1;\n    while (l &lt; r)\n    {\n        area = max(area, min(height[l], height[r]) * (r - l));\n        if (height[l] &lt; height[r])\n        {\n            l++;\n        }\n        else\n        {\n            r--;\n        }\n    }\n    return area;\n}</code></pre>\n<p>C++解法：</p>\n<pre class=\"language-cpp\"><code>class Solution\n{\npublic:\n    int maxArea(vector&lt;int&gt; &amp;height)\n    {\n        int area = 0, l = 0, r = height.size() - 1;\n        while (l &lt; r)\n        {\n            area = max(area, min(height[l], height[r]) * (r - l));\n            if (height[l] &lt; height[r])\n            {\n                l++;\n            }\n            else\n            {\n                r--;\n            }\n        }\n        return area;\n    }\n};</code></pre>\n<p>JAVA解法：</p>\n<pre class=\"language-java\"><code>class Solution {\n    public int maxArea(int[] height) {\n        int area = 0, l = 0, r = height.length - 1;\n        while (l &lt; r) {\n            area = Math.max(area, Math.min(height[l], height[r]) * (r - l));\n            if (height[l] &lt; height[r]) {\n                l++;\n            } else {\n                r--;\n            }\n        }\n        return area;\n    }\n}\n</code></pre>\n<p>Python3解法：</p>\n<pre class=\"language-python\"><code>class Solution(object):\n    def maxArea(self, height):\n        area, l, r = 0, 0, len(height)-1\n        while l &lt; r:\n            area = max(area, min(height[l], height[r]) * (r - l))\n            if height[l] &lt; height[r]:\n                l += 1\n            else:\n                r += 1\n        return area</code></pre>', 32, '2019-12-22 14:38:04', 156, 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sequence` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '/icon/java.png', 'JAVA', 1);
INSERT INTO `category` VALUES (2, '/icon/cpp.png', 'C++', 2);
INSERT INTO `category` VALUES (3, '/icon/python.png', 'Python', 3);
INSERT INTO `category` VALUES (4, '/icon/golang.png', 'Golang', 4);
INSERT INTO `category` VALUES (5, '/icon/android.png', 'Android', 5);
INSERT INTO `category` VALUES (6, '/icon/linux.png', 'Linux', 6);
INSERT INTO `category` VALUES (7, '/icon/windows.png', 'Windows', 7);
INSERT INTO `category` VALUES (8, '/icon/miniprogram.png', '小程序', 8);
INSERT INTO `category` VALUES (9, '/icon/spring.png', 'Spring', 9);
INSERT INTO `category` VALUES (10, '/icon/springboot.png', 'SpringBoot', 10);
INSERT INTO `category` VALUES (11, '/icon/springcloud.png', 'SpringCloud', 11);
INSERT INTO `category` VALUES (12, '/icon/springsecurity.png', 'SpringSecurity', 12);
INSERT INTO `category` VALUES (13, '/icon/mybatis.png', 'Mybatis', 13);
INSERT INTO `category` VALUES (14, '/icon/hibernate.png', 'Hibernate', 14);
INSERT INTO `category` VALUES (15, '/icon/kafka.png', 'Kafka', 15);
INSERT INTO `category` VALUES (16, '/icon/zookeeper.png', 'Zookeeper', 16);
INSERT INTO `category` VALUES (17, '/icon/html.png', 'HTML', 17);
INSERT INTO `category` VALUES (18, '/icon/css.png', 'CSS', 18);
INSERT INTO `category` VALUES (19, '/icon/javascript.png', 'JavaScript', 19);
INSERT INTO `category` VALUES (20, '/icon/typescript.png', 'TypeScript', 20);
INSERT INTO `category` VALUES (21, '/icon/vue.png', 'Vue', 21);
INSERT INTO `category` VALUES (22, '/icon/nuxt.png', 'Nuxt', 22);
INSERT INTO `category` VALUES (23, '/icon/elementui.png', 'ElementUI', 23);
INSERT INTO `category` VALUES (24, '/icon/bootstrap.png', 'Bootstrap', 24);
INSERT INTO `category` VALUES (25, '/icon/nginx.png', 'Nginx', 25);
INSERT INTO `category` VALUES (26, '/icon/nodejs.png', 'Nodejs', 26);
INSERT INTO `category` VALUES (27, '/icon/mysql.png', 'Mysql', 27);
INSERT INTO `category` VALUES (28, '/icon/redis.png', 'Redis', 28);
INSERT INTO `category` VALUES (29, '/icon/git.png', 'Git', 29);
INSERT INTO `category` VALUES (30, '/icon/maven.png', 'Maven', 30);
INSERT INTO `category` VALUES (31, '/icon/docker.png', 'Docker', 31);
INSERT INTO `category` VALUES (32, '/icon/algorithm.png', '算法题解', 32);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `article_id` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '时间复杂度和空间复杂度的取舍很重要。', 1, 1, '2019-12-22 15:05:54');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'member');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'kwxy1314@qq.com', 'Linter', '$2a$10$en2JxasguDAPugsYRPdHGOTazazkihMqq3gC9yi8a8yBeQhpwFgCu');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
