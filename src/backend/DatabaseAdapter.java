package backend;

/**
 * A class intended to interact with JDBC. This acts as a
 * shim layer that sits in between JDBC and exposes an
 * interface that the GUI frontend is intended to call to
 * perform specified tasks on the database. These operations
 * can be split into two types of operations:
 * - query
 * - set.
 * <p>
 * The set operations can be further divided into two different sub-operations:
 * - add
 * - change
 * <p>
 * The naming conventions of the operations exposed in this
 * class will follow the above divisions for the sake of consistency.
 * Furthermore, this shim layer will obey the following principles of a REST API:
 * <p>
 * - Client-server communication
 * - Stateless communication
 * - Uniform interface
 * <p>
 * Following these 3 principles are intended to again, increase consistency with the behavior of this shim layer.
 */
public class DatabaseAdapter {
}
