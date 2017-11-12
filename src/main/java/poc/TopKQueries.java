package poc;

import java.util.*;

/**
 * Created by slava on 12/11/17.
 */
public class TopKQueries {

    class Query {
        String string;
        Integer frequency;
    }

    class ListPriorityQueue {
        LinkedList<Query> queryList;
        int maxSize;

        public ListPriorityQueue(int maxSize) {
            if (maxSize < 1) {
                throw new IllegalArgumentException("maxSize must be >=1");
            }
            this.maxSize = maxSize;
            queryList = new LinkedList<>();
        }

        public boolean add(Query query) {
            if (queryList.isEmpty()) {
                return queryList.add(query);
            }
            int insertIndex = findPosition(query.frequency, 0, queryList.size()-1);
//            Collections.binarySearch(queryList, query, comparator);
            queryList.add(insertIndex, query);
            if (queryList.size() > maxSize) {
                queryList.pollLast();
            }
            return true;
        }

        /**
         * binary search: log n
         * @param frequency
         * @param start
         * @param end
         * @return
         */
        private int findPosition(int frequency, int start, int end) {
            if (start >= end) {
                return start;
            }
            int middle = (end - start)/2;
            int middleFrequency = queryList.get(middle).frequency;
            if (frequency > middleFrequency) {
                return findPosition(frequency, start, middle);
            } else {
                return findPosition(frequency, middle+1, end);
            }
        }

        public Query get(int index) {
            return queryList.get(index);
        }

        public List<Query> getTopK(int k) {
            return queryList.subList(0, k);
        }

    }

    public List<Query> getTopQueries(List<Query> queries, int k) {
        ListPriorityQueue queue = new ListPriorityQueue(k);
        for (Query q : queries) {
            queue.add(q);
        }
        return queue.getTopK(k);
    }


    /*class QueryFrequencyComparator implements Comparator<Query> {

        boolean asc;

        public QueryFrequencyComparator(boolean asc) {
            this.asc = asc;
        }

        @Override
        public int compare(Query q1, Query q2) {
            if (asc) {
                return q1.frequency.compareTo(q2.frequency);
            } else {
                return - q1.frequency.compareTo(q2.frequency);
            }
        }
    }*/


    /**
     * add: logarithmic (for a randomly ordered input)
     * poll: logarithmic
     */
    /*class TreePriorityQueue {

        class PriorityNode {
            Query query;

            PriorityNode left;
            PriorityNode right;
            PriorityNode parent;


            public PriorityNode(Query query) {
                this.query = query;
            }

            private PriorityNode findParent(PriorityNode node) {
                if (comparator.compare(node.query, query) < 0) {
                    if (left == null) {
                        return this;
                    } else {
                        return left.findParent(node);
                    }
                } else {
                    if (right == null) {
                        return this;
                    } else {
                        return right.findParent(node);
                    }
                }
            }

            private PriorityNode findHead() {
                if (right == null) {
                    return this;
                }
                return right.findHead();
            }
        }

        QueryFrequencyComparator comparator;

        PriorityNode root;
        PriorityNode head;

        int size;

        public TreePriorityQueue() {
            comparator = new QueryFrequencyComparator(false);
        }

        boolean isEmpty() {
            return root == null;
        }

        int size() {
            return size;
        }

        boolean add(Query query) {
            PriorityNode node = new PriorityNode(query);
            if (root == null) {
                root = node;
                head = node;
                size = 1;
                return true;
            }
            PriorityNode parent = root.findParent(node);
            if (parent == null) {
                return false;
            }
            node.parent = parent;
            if (comparator.compare(node.query, parent.query) < 0) {
                parent.left = node;
            } else {
                parent.right = node;
                if (parent == head) {
                    head = node;
                }
            }
            size++;
            return true;
        }

        Query poll() {
            PriorityNode res = head;
            PriorityNode parent = head.parent;
            size--;
            if (parent == null) {
                root = null;
                head = null;
                return res.query;
            }
            if (res.left == null) {
                head = parent;
                parent.right = null;
            } else {
                head = res.left.findHead();
                parent.right = res.left;
            }
            return res.query;
        }

        List<Query> pollTopK(int k) {
            List<Query> res = new ArrayList<>(k);
            for (int i=0; i<k; i++) {
                if (isEmpty()) {
                    break;
                }
                res.add(poll());
            }
            return res;
        }


        List<Query> getTopK(int k) {
            List<Query> res = new ArrayList<>(k);
            PriorityNode current = head;
            while (res.size() < k && current != null) {
                res.add(current.query);
                if (current.left != null) {
                    current = current.left.findHead();
                } else {
                    current = current.parent;
                }
            }
            return res;
        }

    }*/




}
